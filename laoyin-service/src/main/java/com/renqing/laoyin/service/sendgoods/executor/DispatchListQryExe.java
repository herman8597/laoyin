package com.bat.laoyin.service.sendgoods.executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.common.ErrorCode;
import com.bat.laoyin.api.common.LaoYinException;
import com.bat.laoyin.api.logistics.LogisticsService;
import com.bat.laoyin.api.logistics.dto.KDNOrderOnlineReq;
import com.bat.laoyin.api.logistics.dto.KDNOrderOnlineResp;
import com.bat.laoyin.api.sendgoods.dto.DispatchListQry;
import com.bat.laoyin.api.sendgoods.dto.data.DispatchListDTO;
import com.bat.laoyin.api.sendgoods.dto.data.DispatchListItemDTO;
import com.bat.laoyin.api.sendgoods.dto.data.DispatchListItemItemDTO;
import com.bat.laoyin.api.sendgoods.dto.data.DispatchListOrderDetailDTO;
import com.bat.laoyin.dao.goods.GoodsMapper;
import com.bat.laoyin.dao.goods.dataobject.GoodsDO;
import com.bat.laoyin.dao.makeup.MakeupTaskMaterialMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskMaterialDO;
import com.bat.laoyin.dao.order.OrderDeliverMapper;
import com.bat.laoyin.dao.order.OrderDetailMapper;
import com.bat.laoyin.dao.order.OrderInfoMapper;
import com.bat.laoyin.dao.order.dataobject.OrderDeliverDO;
import com.bat.laoyin.dao.order.dataobject.OrderDetailDO;
import com.bat.laoyin.dao.order.dataobject.OrderInfoDO;
import com.bat.laoyin.dao.sendgoods.DispatchListMapper;
import com.bat.laoyin.dao.sendgoods.DispatchListOrderDetailMapper;
import com.bat.laoyin.dao.sendgoods.SendGoodsAddressMapper;
import com.bat.laoyin.dao.sendgoods.SendGoodsDistributionModeMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListDO;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListOrderDetailDO;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsAddressDO;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsDistributionModeDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.common.utils.logistics.KDNAdressConvertor;
import com.bat.laoyin.service.sendgoods.convertor.DispatchListConvertor;
import com.bat.laoyin.service.sendgoods.convertor.DispatchListOrderDetailConvertor;

import lombok.SneakyThrows;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class DispatchListQryExe extends ServiceQryImpl<DispatchListMapper, DispatchListDO> {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private DispatchListMapper dispatchListMapper;

    @Resource
    private DispatchListOrderDetailMapper dispatchListOrderDetailMapper;

    @Resource
    private SendGoodsDistributionModeMapper distributionModeMapper;

    @Resource
    private MakeupTaskMaterialMapper makeupTaskMaterialMapper;

    @Resource
    private LogisticsService logisticsService;

    @Resource
    private SendGoodsDistributionModeMapper sendGoodsDistributionModeMapper;

    @Resource
    private SendGoodsAddressMapper sendGoodsAddressMapper;

    @Resource
    private OrderDeliverMapper orderDeliverMapper;

    public List<DispatchListDO> selectVo(DispatchListQry qry) {
        DispatchListDO aDo = DispatchListConvertor.toDispatchListDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return dispatchListMapper.selectPageVo(aDo, sort);
    }

    /**
     * 配送方式列表 类似发货单列表 数据结果稍微不一样
     *
     * @param page
     * @param qry
     * @return
     */
    public IPage<DispatchListDTO> selectPageVo(IPage<DispatchListDO> page, DispatchListQry qry) {
        DispatchListDO aDo = DispatchListConvertor.toDispatchListDO(qry);
        List<Integer> dispatchListId = new ArrayList<>();
        if (StringUtils.isNotBlank(qry.getOrderNo()) || StringUtils.isNotBlank(qry.getDistributionCode())) {
            DispatchListOrderDetailDO detailDO = new DispatchListOrderDetailDO();
            detailDO.setOrderNo(qry.getOrderNo());
            detailDO.setDistributionCode(qry.getDistributionCode());
            List<DispatchListOrderDetailDO> detailDOS = dispatchListOrderDetailMapper.selectPageVo(detailDO, null);
            Map<Integer, List<DispatchListOrderDetailDO>> listMap =
                detailDOS.stream().collect(Collectors.groupingBy(DispatchListOrderDetailDO::getDispatchListId));
            dispatchListId = new ArrayList<>(listMap.keySet());
        }
        String sort = SqlUtils.getSort(qry.getSort());
        Map<String, Object> map = new HashMap<>();
        map.put("deliveryStartTime", qry.getDeliveryStartTime());
        map.put("deliveryEndTime", qry.getDeliveryEndTime());
        map.put("dispatchListId", dispatchListId);
        IPage iPage = dispatchListMapper.selectPageVo(page, aDo, sort, map);
        List<DispatchListDO> records = iPage.getRecords();
        List<DispatchListDTO> dispatchListDTOS = records.stream().map(dispatchListDO -> {
            DispatchListDTO dispatchListDTO = DispatchListConvertor.toDispatchListDTO(dispatchListDO);
            dispatchListDTO.setDistributionModeName(
                distributionModeMapper.selectById(dispatchListDTO.getDistributionModeId()).getLogisticsName());
            List<DispatchListOrderDetailDO> doList =
                dispatchListOrderDetailMapper.listByDispatchListId(dispatchListDO.getId());
            Integer orderId = doList.get(0).getOrderId();
            OrderInfoDO orderInfoDO = orderInfoMapper.selectById(orderId);
            dispatchListDTO.setCustomerName(orderInfoDO.getCustomerName());
            List<DispatchListOrderDetailDTO> detailDTOS = doList.stream().map(dispatchListOrderDetailDO -> {
                DispatchListOrderDetailDTO detailDTO =
                    DispatchListOrderDetailConvertor.toDispatchListOrderDetailDTO(dispatchListOrderDetailDO);
                OrderDetailDO detailDO = orderDetailMapper.selectById(dispatchListOrderDetailDO.getOrderDetailId());
                detailDTO.setThirdPartyNo(orderInfoDO.getThirdPartyNo());
                detailDTO.setProductCode(detailDO.getProductCode());
                detailDTO.setProductName(detailDO.getProductName());
                detailDTO.setProductSpecName(detailDO.getProductSpecName());
                detailDTO.setProductBrandName(detailDO.getProductBrandName());
                detailDTO.setItemCount(detailDO.getItemCount());
                detailDTO.setUnitName(goodsMapper.selectById(detailDO.getGoodsId()).getUnitName());
                return detailDTO;
            }).collect(Collectors.toList());
            dispatchListDTO.setDispatchListOrderDetails(detailDTOS);
            return dispatchListDTO;
        }).collect(Collectors.toList());
        iPage.setRecords(dispatchListDTOS);
        return iPage;
    }

    @Transactional(rollbackFor = Exception.class)
    public DispatchListDTO getDispatchList(String distributionCode) {
        int distributionCodeIndex;
        if (distributionCode.contains("-")) {
            String[] split = distributionCode.split("-");
            distributionCode = split[0];
            distributionCodeIndex = Integer.parseInt(split[1]);
        } else {
            distributionCodeIndex = 0;
        }
        MakeupTaskMaterialDO makeupTaskMaterialDO = makeupTaskMaterialMapper
            .getByDistributionCodeAndDistributionCodeIndex(distributionCode, distributionCodeIndex);
        if (makeupTaskMaterialDO == null) {
            throw LaoYinException.buildException(ErrorCode.B_DISTRIBUTION_CODE_NOT_EXIST,
                ErrorCode.B_DISTRIBUTION_CODE_NOT_EXIST_MSG);
        }
        // if (makeupTaskMaterialDO.getScanFlag().equals(SCAN_YES)) {
        // throw LaoYinException.buildException(ErrorCode.B_DISTRIBUTION_CODE_HAS_BEEN_SCANNED,
        // ErrorCode.B_DISTRIBUTION_CODE_HAS_BEEN_SCANNED_MSG);
        // } else {
        // makeupTaskMaterialDO.setScanFlag(SCAN_YES);
        // makeupTaskMaterialDO.setUpdatedAt(new Date());
        // makeupTaskMaterialMapper.updateById(makeupTaskMaterialDO);
        // }

        DispatchListOrderDetailDO byDistributionCode =
            dispatchListOrderDetailMapper.getByDistributionCode(distributionCode);
        DispatchListDO dispatchListDO = dispatchListMapper.selectById(byDistributionCode.getDispatchListId());
        dispatchListDO.setScanSum(dispatchListDO.getScanSum() + 1);
        dispatchListMapper.updateById(dispatchListDO);
        OrderDetailDO detailDO = orderDetailMapper.selectById(makeupTaskMaterialDO.getOrderDetailId());
        DispatchListDTO dispatchListDTO = DispatchListConvertor.toDispatchListDTO(dispatchListDO);
        dispatchListDTO.setPdfUrl(detailDO.getLabelFilePath());
        // 第一次扫描 代表没有打印过
        if (dispatchListDTO.getScanSum() == 1) {
            // 一个订单 只有一个商品 商品只有一件
            if (distributionCodeIndex == 0) {
                dispatchListDTO.setFileType(2);
            }
            dispatchListDTO.setDistributionModeName(
                distributionModeMapper.selectById(dispatchListDTO.getDistributionModeId()).getLogisticsName());
            // 同一发货单下的 订单集合 不合单 应该只有一个
            List<DispatchListOrderDetailDO> listOrderDetailDOS =
                dispatchListOrderDetailMapper.listByDispatchListIdGroupByOrderId(dispatchListDO.getId());
            List<DispatchListItemDTO> itemDTOS = listOrderDetailDOS.stream().map(dispatchListOrderDetailDO -> {
                OrderInfoDO orderInfoDO = orderInfoMapper.selectById(dispatchListOrderDetailDO.getOrderId());
                DispatchListItemDTO itemDTO = new DispatchListItemDTO();
                itemDTO.setOrderId(dispatchListOrderDetailDO.getOrderId());
                itemDTO.setOrderNo(dispatchListOrderDetailDO.getOrderNo());
                itemDTO.setThirdPartyNo(orderInfoDO.getThirdPartyNo());
                List<DispatchListOrderDetailDO> detailDOS =
                    dispatchListOrderDetailMapper.listByOrderId(dispatchListOrderDetailDO.getOrderId());
                List<DispatchListItemItemDTO> itemItemDTOS = detailDOS.stream().map(dispatchListOrderDetailDO1 -> {
                    OrderDetailDO orderDetailDO =
                        orderDetailMapper.selectById(dispatchListOrderDetailDO1.getOrderDetailId());
                    GoodsDO goodsDO = goodsMapper.selectById(orderDetailDO.getGoodsId());
                    DispatchListItemItemDTO itemItemDTO = new DispatchListItemItemDTO();
                    // itemItemDTO.setDistributionCode(dispatchListOrderDetailDO1.getDistributionCode());
                    itemItemDTO.setDistributionCode("test");
                    itemItemDTO.setGoodsName(goodsDO.getName());
                    // itemItemDTO.setSku(goodsDO.getCode());
                    itemItemDTO.setSku("sku");
                    itemItemDTO.setProductSpecName(orderDetailDO.getProductSpecName());
                    itemItemDTO.setUnitName(goodsDO.getUnitName());
                    itemItemDTO.setItemCount(orderDetailDO.getItemCount());
                    return itemItemDTO;
                }).collect(Collectors.toList());
                itemDTO.setDispatchListItemItems(itemItemDTOS);
                return itemDTO;
            }).collect(Collectors.toList());
            dispatchListDTO.setDispatchListItems(itemDTOS);
            return dispatchListDTO;
        } else {
            return dispatchListDTO;
        }
    }

    @SneakyThrows
    public KDNOrderOnlineResp getWaybill(String dispatchListCode) {
        DispatchListDO dispatchListDO = dispatchListMapper.getByCode(dispatchListCode);
        List<DispatchListOrderDetailDO> orderDetailDOS =
            dispatchListOrderDetailMapper.listByDispatchListId(dispatchListDO.getId());
        for (DispatchListOrderDetailDO orderDetailDO : orderDetailDOS) {
            // 此处配货编码 不带杠索引
            List<MakeupTaskMaterialDO> materialDOS =
                makeupTaskMaterialMapper.listByDistributionCode(orderDetailDO.getDistributionCode());
            boolean anyMatch =
                materialDOS.stream().anyMatch(makeupTaskMaterialDO -> makeupTaskMaterialDO.getScanFlag() == 0);
            if (anyMatch) {
                // 如果其中有任意一个没有扫码 代表货没有配齐
                // throw LaoYinException.buildException(ErrorCode.B_DISPATCH_LIST_ITEM_NOT_ENOUGH,
                // ErrorCode.B_DISPATCH_LIST_ITEM_NOT_ENOUGH_MSG);
            }
        }
        SendGoodsDistributionModeDO distributionModeDO =
            sendGoodsDistributionModeMapper.selectById(dispatchListDO.getDistributionModeId());
        KDNOrderOnlineReq kdnOrderOnlineReq = new KDNOrderOnlineReq();
        // kdnOrderOnlineReq.setOrderCode(System.currentTimeMillis() + "");
        kdnOrderOnlineReq.setOrderCode(dispatchListDO.getCode());
        kdnOrderOnlineReq.setShipperCode(distributionModeDO.getLogisticsCode());
        kdnOrderOnlineReq.setCustomerName(distributionModeDO.getCustomerName());
        kdnOrderOnlineReq.setCustomerPwd(distributionModeDO.getCustomerPwd());
        kdnOrderOnlineReq.setMonthCode(distributionModeDO.getMonthCode());
        kdnOrderOnlineReq.setSendSite(distributionModeDO.getSendSite());
        kdnOrderOnlineReq.setPayType(distributionModeDO.getPayType());
        kdnOrderOnlineReq.setExpType(1);
        SendGoodsAddressDO addressDO = sendGoodsAddressMapper.selectById(distributionModeDO.getAddressId());
        KDNOrderOnlineReq.SenderDTO senderDTO = new KDNOrderOnlineReq.SenderDTO();
        senderDTO.setName(addressDO.getSenderName());
        senderDTO.setMobile(addressDO.getSenderMobile());
        senderDTO.setProvinceName(addressDO.getProvinceName());
        senderDTO.setCityName(addressDO.getCityName());
        senderDTO.setExpAreaName(addressDO.getAreaName());
        senderDTO.setAddress(addressDO.getDetailedAddress());
        kdnOrderOnlineReq.setSender(senderDTO);
        OrderDeliverDO deliverDO = orderDeliverMapper.selectById(dispatchListDO.getOrderDeliverId());
        KDNOrderOnlineReq.ReceiverDTO receiverDTO = new KDNOrderOnlineReq.ReceiverDTO();
        receiverDTO.setName(deliverDO.getConsignee());
        receiverDTO.setMobile(deliverDO.getMobile());
        receiverDTO.setProvinceName(deliverDO.getProvinceName());
        receiverDTO.setCityName(deliverDO.getCityName());
        receiverDTO.setExpAreaName(deliverDO.getAreaName());
        receiverDTO.setAddress(deliverDO.getDetailedAddress());
        kdnOrderOnlineReq.setReceiver(receiverDTO);
        kdnOrderOnlineReq.setQuantity(1);
        List<KDNOrderOnlineReq.CommodityDTO> commodityDTOS = new ArrayList<>();
        KDNOrderOnlineReq.CommodityDTO commodityDTO = new KDNOrderOnlineReq.CommodityDTO();
        commodityDTO.setGoodsName("定制商品");
        commodityDTOS.add(commodityDTO);
        kdnOrderOnlineReq.setCommodity(commodityDTOS);
        // 特殊地址处理
        KDNAdressConvertor.convertor(kdnOrderOnlineReq);
        return logisticsService.orderOnline(kdnOrderOnlineReq);
    }
}
