package com.bat.laoyin.service.order.executor;

import static com.bat.laoyin.service.common.constants.Constant.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.api.makeup.MakeupTaskService;
import com.bat.laoyin.api.order.dto.data.OrderDetailDTO;
import com.bat.laoyin.api.order.dto.data.OrderInfoDTO;
import com.bat.laoyin.api.third.dto.OrderDeliverCreateCmd;
import com.bat.laoyin.api.third.dto.OrderDetailCreateCmd;
import com.bat.laoyin.api.third.dto.OrderInfoCreateCmd;
import com.bat.laoyin.api.third.dto.ThirdOrderCreateCmd;
import com.bat.laoyin.dao.customer.CustomerMapper;
import com.bat.laoyin.dao.customer.dataobject.CustomerDO;
import com.bat.laoyin.dao.goods.GoodsMapper;
import com.bat.laoyin.dao.goods.dataobject.GoodsDO;
import com.bat.laoyin.dao.order.OrderDeliverMapper;
import com.bat.laoyin.dao.order.OrderDetailMapper;
import com.bat.laoyin.dao.order.OrderInfoMapper;
import com.bat.laoyin.dao.order.dataobject.OrderDeliverDO;
import com.bat.laoyin.dao.order.dataobject.OrderDetailDO;
import com.bat.laoyin.dao.order.dataobject.OrderInfoDO;
import com.bat.laoyin.dao.product.ProductBrandMapper;
import com.bat.laoyin.dao.product.ProductMapper;
import com.bat.laoyin.dao.product.ProductSpecMapper;
import com.bat.laoyin.dao.product.dataobject.ProductBrandDO;
import com.bat.laoyin.dao.product.dataobject.ProductDO;
import com.bat.laoyin.dao.product.dataobject.ProductSpecDO;
import com.bat.laoyin.dao.sendgoods.SendGoodsDistributionModeMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsDistributionModeDO;
import com.bat.laoyin.dao.tenant.TenantMapper;
import com.bat.laoyin.dao.tenant.dataobject.TenantDO;
import com.bat.laoyin.service.common.enums.OrderDetailStatus;
import com.bat.laoyin.service.common.enums.OrderStatus;
import com.bat.laoyin.service.common.enums.OrderType;
import com.bat.laoyin.service.common.utils.NoUtils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
@Slf4j
public class OrderInfoCmdExe extends ServiceCmdImpl<OrderInfoMapper, OrderInfoDO> {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private TenantMapper tenantMapper;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private OrderDeliverMapper orderDeliverMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductSpecMapper productSpecMapper;

    @Resource
    private ProductBrandMapper productBrandMapper;

    @Resource
    private SendGoodsDistributionModeMapper distributionModeMapper;

    @Resource
    private MakeupTaskService makeupTaskService;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;

    /**
     * 编程式事务 原因很复杂 。保存订单 与拼版 事务不能互相影响，不能说拼版失败 客户订单数据都没了。
     *
     * 拼版要先有订单数据 所以订单事务要先提交 所以也不能用PROPAGATION__REQUIRES_NEW。订单事务要是挂起 拼版没有订单数据
     * 
     * @param cmd
     */
    // @Transactional(rollbackFor = Exception.class)
    public void mySave(ThirdOrderCreateCmd cmd) {
        OrderDeliverCreateCmd orderDeliver = cmd.getOrderDeliver();
        // 传空选默认发货平台
        if (StringUtils.isBlank(orderDeliver.getPlatformCode())) {
            orderDeliver.setPlatformCode(DEFAULT_PLATFORM_CODE);
        }
        TransactionStatus transaction = null;
        try {
            transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);

            // 获取客户信息 与 租户信息
            OrderInfoCreateCmd orderInfo = cmd.getOrderInfo();
            CustomerDO customerDO = customerMapper.selectById(orderInfo.getCustomerId());
            TenantDO tenantDO = tenantMapper.selectById(customerDO.getTenantId());
            Date date = new Date();

            // 订单基本信息
            OrderInfoDO orderInfoDO = getOrderInfoDO(orderInfo, tenantDO, date);
            orderInfoMapper.insert(orderInfoDO);

            // 循环处理项目明细
            AtomicReference<Boolean> matchFlag = new AtomicReference<>(true);
            cmd.getOrderDetails().forEach(orderDetail -> matchFlag
                .set(itemProcess(customerDO, tenantDO, date, orderInfoDO, orderDeliver, orderDetail)));
            // 订单的配送方式
            OrderDeliverDO deliverDO = getOrderDeliverDO(cmd.getOrderDeliver(), tenantDO, date, orderInfoDO.getId());
            orderDeliverMapper.insert(deliverDO);
            if (matchFlag.get()) {
                orderInfoDO.setStatus(OrderStatus.WAIT_PRODUCED.getValue());
                orderInfoMapper.updateById(orderInfoDO);
                dataSourceTransactionManager.commit(transaction);
                makeupTaskService.makeupOrder(orderInfoDO.getId());
            } else {
                dataSourceTransactionManager.commit(transaction);
            }
        } catch (Exception e) {
            if (transaction != null) {
                dataSourceTransactionManager.rollback(transaction);
            }
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 循环处理项目明细
     * 
     * @param customerDO
     * @param tenantDO
     * @param date
     * @param orderInfoDO
     * @param orderDetail
     * @return
     */
    private Boolean itemProcess(CustomerDO customerDO, TenantDO tenantDO, Date date, OrderInfoDO orderInfoDO,
        OrderDeliverCreateCmd orderDeliver, OrderDetailCreateCmd orderDetail) {
        // 匹配标志
        boolean matchFlag = true;
        LambdaQueryChainWrapper<GoodsDO> goodsChainWrapper = new LambdaQueryChainWrapper<>(goodsMapper);
        // 名称 编码 规格 发货平台编码
        GoodsDO goodsDO = goodsChainWrapper.eq(GoodsDO::getName, orderDetail.getName())
            .eq(GoodsDO::getCode, orderDetail.getCode()).eq(GoodsDO::getSpec, orderDetail.getSpec())
            .eq(GoodsDO::getPlatformCode, orderDeliver.getPlatformCode()).one();
        if (goodsDO == null) {
            // 创建新商品
            // 新增的商品 没有匹配配送方式 肯定匹配不上
            matchFlag = false;
            goodsDO = getGoodsDO(orderDetail, customerDO.getId(), tenantDO, date);
            goodsDO.setPlatformCode(orderDeliver.getPlatformCode());
            // 如果是新建商品 第三方也有传产品编码 产品编码也能对上，使用客户传的
            if (StringUtils.isNotBlank(orderDetail.getProductCode())
                && productMapper.getByCode(orderDetail.getProductCode()) != null) {
                goodsDO.setProductCode(orderDetail.getProductCode());
            }
            goodsMapper.insert(goodsDO);
        } else {
            if (goodsDO.getDistributionModeId() == null) {
                log.info("商品:{}对应的配送方式id为空", goodsDO.getCode());
                matchFlag = false;
            }
        }
        // 创建订单明细
        OrderDetailDO detailDO = getOrderDetailDO(orderDetail, tenantDO, date, orderInfoDO.getId(), goodsDO);
        orderDetailMapper.insert(detailDO);
        return matchFlag;
    }

    /**
     * 订单基本信息
     *
     * @param orderInfo
     * @param tenantDO
     * @param date
     * @return
     */
    private OrderInfoDO getOrderInfoDO(OrderInfoCreateCmd orderInfo, TenantDO tenantDO, Date date) {
        // 订单信息
        OrderInfoDO orderInfoDO = new OrderInfoDO();
        BeanUtils.copyProperties(orderInfo, orderInfoDO);
        // 用客户的租户联系人做创建人等信息
        orderInfoDO.setTenantId(tenantDO.getId());
        orderInfoDO.setCreatedBy(tenantDO.getContactId());
        orderInfoDO.setUpdatedBy(tenantDO.getContactId());
        orderInfoDO.setCreatedAt(date);
        orderInfoDO.setUpdatedAt(date);
        orderInfoDO.setOrderNo(NoUtils.getOrderNo());
        orderInfoDO.setDeleteFlag(DEL_NO);
        // 订单状态要处理
        orderInfoDO.setStatus(OrderStatus.WAIT_CHECKED.getValue());
        orderInfoDO.setOrderType(OrderType.API_IMPORT.getValue());
        // 审核人 TODO
        orderInfoDO.setCheckedBy(tenantDO.getContactId());
        return orderInfoDO;
    }

    /**
     * 拼装商品
     * 
     * @param orderDetail
     * @param customerId
     * @param tenantDO
     * @param date
     * @return
     */
    private GoodsDO getGoodsDO(OrderDetailCreateCmd orderDetail, Integer customerId, TenantDO tenantDO, Date date) {
        GoodsDO goodsDO = new GoodsDO();
        BeanUtils.copyProperties(orderDetail, goodsDO);
        goodsDO.setDistributionModeId(null);
        goodsDO.setPlatformCode(null);
        goodsDO.setProductCode(null);
        goodsDO.setTenantId(tenantDO.getId());
        goodsDO.setCreatedBy(tenantDO.getContactId());
        goodsDO.setUpdatedBy(tenantDO.getContactId());
        goodsDO.setCreatedAt(date);
        goodsDO.setUpdatedAt(date);
        goodsDO.setDeleteFlag(DEL_NO);
        goodsDO.setStatus(OPEN_YES);
        goodsDO.setSku(NoUtils.getGoodsNo());
        goodsDO.setCustomerId(customerId);
        return goodsDO;
    }

    /**
     * 拼装订单详情
     * 
     * @param tenantDO
     * @param date
     * @param orderId
     * @param orderDetail
     * @param goods
     * @return
     */
    private OrderDetailDO getOrderDetailDO(OrderDetailCreateCmd orderDetail, TenantDO tenantDO, Date date,
        Integer orderId, GoodsDO goods) {
        OrderDetailDO detailDO = new OrderDetailDO();
        BeanUtils.copyProperties(orderDetail, detailDO);
        detailDO.setPlatformCode(goods.getPlatformCode());
        detailDO.setDistributionModeId(goods.getDistributionModeId());
        if (StringUtils.isNotBlank(goods.getProductCode())) {
            // 产品编码
            detailDO.setProductCode(goods.getProductCode());
            ProductDO byCode = productMapper.getByCode(goods.getProductCode());
            if (byCode != null) {
                // 产品名称
                detailDO.setProductName(byCode.getName());
                if (byCode.getProductSpecId() != null) {
                    ProductSpecDO productSpecDO = productSpecMapper.selectById(byCode.getProductSpecId());
                    if (productSpecDO != null) {
                        // 产品规格名称
                        detailDO.setProductSpecName(productSpecDO.getSimpleSpecName());
                    }
                }
                Integer productBrandId = byCode.getProductBrandId();
                if (productBrandId != null) {
                    ProductBrandDO productBrandDO = productBrandMapper.selectById(productBrandId);
                    if (productBrandDO != null) {
                        // 产品品牌名称
                        detailDO.setProductBrandName(productBrandDO.getName());
                    }
                }

            }
        }
        detailDO.setTenantId(tenantDO.getId());
        detailDO.setCreatedBy(tenantDO.getContactId());
        detailDO.setUpdatedBy(tenantDO.getContactId());
        detailDO.setCreatedAt(date);
        detailDO.setUpdatedAt(date);
        detailDO.setDeleteFlag(DEL_NO);
        detailDO.setStatus(OrderDetailStatus.IN_MAKEUP.getValue());
        detailDO.setOrderId(orderId);
        detailDO.setGoodsId(goods.getId());
        return detailDO;
    }

    /**
     * 拼装发货信息
     * 
     * @param orderDeliver
     * @param tenantDO
     * @param date
     * @param orderId
     * @return
     */
    private OrderDeliverDO getOrderDeliverDO(OrderDeliverCreateCmd orderDeliver, TenantDO tenantDO, Date date,
        Integer orderId) {
        OrderDeliverDO deliverDO = new OrderDeliverDO();
        BeanUtils.copyProperties(orderDeliver, deliverDO);
        deliverDO.setTenantId(tenantDO.getId());
        deliverDO.setCreatedBy(tenantDO.getContactId());
        deliverDO.setUpdatedBy(tenantDO.getContactId());
        deliverDO.setCreatedAt(date);
        deliverDO.setUpdatedAt(date);
        deliverDO.setDeleteFlag(DEL_NO);
        deliverDO.setStatus(OPEN_YES);
        // 省市区id 匹配
        deliverDO.setOrderId(orderId);
        return deliverDO;
    }

    /**
     * 全量更新
     * 
     * @param newObj
     */
    @Transactional(rollbackFor = Exception.class)
    public void myUpdatePutById(OrderInfoDTO newObj) {
        try {
            log.info("审核订单 订单更新");
            Date date = new Date();
            // 订单基本信息
            OrderInfoDO orderInfoDO = orderInfoMapper.selectById(newObj.getId());
            orderInfoDO.setStatus(newObj.getStatus());
            orderInfoDO.setRemark(newObj.getRemark());
            orderInfoDO.setUpdatedAt(date);
            orderInfoDO.setUpdatedBy(newObj.getUpdatedBy());
            orderInfoMapper.updateById(orderInfoDO);
            // 订单详情
            List<OrderDetailDO> oldOrderDO = orderDetailMapper.listByOrderId(newObj.getId());
            List<OrderDetailDTO> detail = newObj.getDetail();
            List<Integer> orderIds = detail.stream().map(OrderDetailDTO::getId).collect(Collectors.toList());
            List<OrderDetailDO> newOrderDO = orderDetailMapper.selectBatchIds(orderIds);
            oldOrderDO.removeAll(newOrderDO);
            // 订单详情删除
            if (!CollectionUtils.isEmpty(oldOrderDO)) {
                List<Integer> collect = oldOrderDO.stream().map(OrderDetailDO::getId).collect(Collectors.toList());
                log.info("需要删除的订单明细集合:{}", collect);
                orderDetailMapper.deleteBatchIds(collect);
            }
            // 订单详情更新
            detail.forEach(orderDetailDTO -> {
                OrderDetailDO detailDO = newOrderDO.stream()
                    .filter(orderDetailDO -> orderDetailDTO.getId().equals(orderDetailDO.getId())).findFirst().get();
                GoodsDO aDo = goodsMapper.selectById(detailDO.getGoodsId());
                // 更新 产品与商品的关联关系
                if (StringUtils.isNotBlank(orderDetailDTO.getProductCode())) {
                    ProductDO byCode = productMapper.getByCode(orderDetailDTO.getProductCode());
                    if (byCode != null) {
                        detailDO.setProductCode(orderDetailDTO.getProductCode());
                        detailDO.setProductName(byCode.getName());
                        ProductSpecDO productSpecDO = productSpecMapper.selectById(byCode.getProductSpecId());
                        if (productSpecDO != null) {
                            detailDO.setProductSpecName(productSpecDO.getSimpleSpecName());
                        }
                        if (StringUtils.isBlank(aDo.getProductCode())) {
                            aDo.setProductCode(orderDetailDTO.getProductCode());
                        }
                    }
                }
                // 更新 配送方式的关联关系
                if (orderDetailDTO.getDistributionModeId() != null) {
                    SendGoodsDistributionModeDO modeDO =
                        distributionModeMapper.selectById(orderDetailDTO.getDistributionModeId());
                    if (modeDO != null && modeDO.getPlatformCode().contains(aDo.getPlatformCode())) {
                        if (aDo.getDistributionModeId() == null) {
                            aDo.setDistributionModeId(orderDetailDTO.getDistributionModeId());
                        }
                    }
                }
                goodsMapper.updateById(aDo);
                if (StringUtils.isNotBlank(orderDetailDTO.getFilePath())) {
                    detailDO.setFilePath(orderDetailDTO.getFilePath());
                }
                orderDetailMapper.updateById(detailDO);
            });
            // 发货信息
            OrderDeliverDO deliverDO = orderDeliverMapper.selectById(newObj.getDeliver().getId());
            BeanUtil.copyProperties(newObj.getDeliver(), deliverDO, CopyOptions.create().setIgnoreNullValue(true));
            orderDeliverMapper.updateById(deliverDO);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

}
