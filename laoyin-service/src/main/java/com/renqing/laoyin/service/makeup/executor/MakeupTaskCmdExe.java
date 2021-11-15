package com.bat.laoyin.service.makeup.executor;

import static com.bat.laoyin.api.common.ErrorCode.*;
import static com.bat.laoyin.service.common.constants.Constant.*;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.api.common.LaoYinException;
import com.bat.laoyin.api.print.PrintService;
import com.bat.laoyin.api.xxljob.api.dto.XxlJobRpcCmd;
import com.bat.laoyin.api.xxljob.service.XxlJobService;
import com.bat.laoyin.dao.makeup.MakeupSettingDeadlineMapper;
import com.bat.laoyin.dao.makeup.MakeupSettingMapper;
import com.bat.laoyin.dao.makeup.MakeupSettingProductMapper;
import com.bat.laoyin.dao.makeup.MakeupTaskMapper;
import com.bat.laoyin.dao.makeup.dataobject.*;
import com.bat.laoyin.dao.order.OrderDeliverMapper;
import com.bat.laoyin.dao.order.OrderInfoMapper;
import com.bat.laoyin.dao.order.dataobject.OrderDetailDO;
import com.bat.laoyin.dao.order.dataobject.OrderInfoDO;
import com.bat.laoyin.dao.product.ProductMapper;
import com.bat.laoyin.dao.product.dataobject.ProductDO;
import com.bat.laoyin.dao.sendgoods.DispatchListMapper;
import com.bat.laoyin.service.common.enums.MakeupTaskStatus;
import com.bat.laoyin.service.common.enums.OrderDetailStatus;
import com.bat.laoyin.service.common.enums.OrderStatus;
import com.bat.laoyin.service.common.handle.ProcessorHolder;
import com.bat.laoyin.service.common.handle.dto.BusinessInfoReq;
import com.bat.laoyin.service.common.utils.CronUtils;
import com.bat.laoyin.service.common.utils.NoUtils;
import com.bat.laoyin.service.common.utils.TimeUtils;
import com.bat.laoyin.service.order.executor.OrderDetailCmdExe;
import com.bat.laoyin.service.sendgoods.executor.DispatchListOrderDetailCmdExe;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
@Slf4j
public class MakeupTaskCmdExe extends ServiceCmdImpl<MakeupTaskMapper, MakeupTaskDO> {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private OrderDetailCmdExe detailCmdExe;

    @Resource
    private OrderDeliverMapper orderDeliverMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private MakeupSettingMapper makeupSetting;

    @Resource
    private MakeupSettingProductMapper makeupSettingProductMapper;

    @Resource
    private MakeupSettingDeadlineMapper makeupSettingDeadlineMapper;

    @Resource
    private MakeupTaskMapper makeupTaskMapper;

    @Resource
    private MakeupTaskProductCmdExe makeupTaskProductCmdExe;

    @Resource
    private MakeupTaskMaterialCmdExe makeupTaskMaterialCmdExe;

    @Resource
    private DispatchListMapper dispatchListMapper;

    @Resource
    private DispatchListOrderDetailCmdExe dispatchListOrderDetailCmdExe;

    @Resource
    private XxlJobService xxlJobService;

    @Value("${xxl.job.actuator.laoyin.id}")
    private Integer actuatorId;

    /**
     * 新建拼版任务的流程
     * 
     * @param orderId
     */
    private void newCreateMakeupTask(Integer orderId, List<MakeupTaskMaterialDO> materials, Integer productId) {
        // A2没有现有的任务，查询拼版设置，新建任务
        MakeupSettingProductDO aDo = makeupSettingProductMapper.getByProductId(productId);
        if (aDo != null) {
            log.info("订单：{}，对应的产品：{}，从拼版设置：{} 新建任务", orderId, productId,
                aDo.getMakeupSettingId());
            MakeupSettingDO settingDO = makeupSetting.selectById(aDo.getMakeupSettingId());
            MakeupTaskDO makeupTaskDO = getMakeupTaskDO(settingDO);
            // 产品关联 拷贝
            copySettingToTask(settingDO, makeupTaskDO);
            insertMakeupTaskMaterial(materials, makeupTaskDO.getId(), productId);
            addMakeupXxlJobTask(makeupTaskDO);
        } else {
            // A3都没有 则代表这个明细对应的产品没有拼版设置，整个订单都不进入生产
            String format = MessageFormat.format("订单：{0}，对应的产品：{1} 没有对应拼版设置", orderId, productId);
            log.info(format);
            throw LaoYinException.buildException(B_MAKEUP_ERROR, format);
        }
    }

    /**
     * 添加谢谢啦 job
     *
     * @param makeupTaskDO
     */
    private void addMakeupXxlJobTask(MakeupTaskDO makeupTaskDO) {
        String jobDesc = "拼版截稿任务-" + makeupTaskDO.getId();
        log.info("创建截稿定时器 名称：{}", jobDesc);
        List<XxlJobRpcCmd> cmds = new ArrayList<>();
        XxlJobRpcCmd cmd = new XxlJobRpcCmd();
        cmd.setAuthor("XXL");
        cmd.setExecutorHandler("makeupDeadLineJobHandler");
        cmd.setJobDesc(jobDesc);
        cmd.setScheduleConf(CronUtils.dateToCronStr(makeupTaskDO.getDeadlineTime()));
        cmd.setJobGroup(actuatorId);
        Map<String, Object> map = new HashMap<>();
        map.put("makeupTaskId", makeupTaskDO.getId());
        cmd.setExecutorParam(JSON.toJSONString(map));
        cmds.add(cmd);
        try {
            xxlJobService.xxlJobAdd(cmds);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    /**
     * 新建拼版任务时 产品与设置关联 拷贝到 产品与任务关联
     *
     * @param settingDO
     * @param makeupTaskDO
     */
    private void copySettingToTask(MakeupSettingDO settingDO, MakeupTaskDO makeupTaskDO) {
        List<MakeupSettingProductDO> settingProductDOS =
            makeupSettingProductMapper.listByMakeupSettingId(settingDO.getId());
        List<MakeupTaskProductDO> taskProductDOS = settingProductDOS.stream().map(makeupSettingProductDO -> {
            MakeupTaskProductDO taskProductDO = new MakeupTaskProductDO();
            BeanUtils.copyProperties(makeupSettingProductDO, taskProductDO);
            taskProductDO.setUpdatedAt(new Date());
            taskProductDO.setMakeupTaskId(makeupTaskDO.getId());
            return taskProductDO;
        }).collect(Collectors.toList());
        makeupTaskProductCmdExe.saveBatch(taskProductDOS);
    }

    /**
     * 新建拼版任务时 拼版任务数据初始化
     *
     * @param settingDO
     * @return
     */
    private MakeupTaskDO getMakeupTaskDO(MakeupSettingDO settingDO) {
        MakeupTaskDO makeupTaskDO = new MakeupTaskDO();
        makeupTaskDO.setDeleteFlag(DEL_NO);
        makeupTaskDO.setStatus(MakeupTaskStatus.WAIT_MAKEUP.getValue());
        makeupTaskDO.setDeadlineStatus(settingDO.getStatus());
        makeupTaskDO.setCode(NoUtils.getMakeUpNo());
        makeupTaskDO.setMakeupSettingId(settingDO.getId());
        makeupTaskDO.setMakeupSettingName(settingDO.getName());
        makeupTaskDO.setMakeupSettingFilePath(settingDO.getFilePath());
        makeupTaskDO.setProductCount(0);
        // 截稿时间取 离当前时间最接近的 拼版设置截稿时间
        MakeupSettingDeadlineDO nextDeadlineTime =
            makeupSettingDeadlineMapper.getNextDeadlineTime(settingDO.getId(), new Date());
        if (nextDeadlineTime == null) {
            throw LaoYinException.buildException(B_MAKEUP_DEADLINE_TIME_NULL, B_MAKEUP_DEADLINE_TIME_NULL_MSG);
        }
        makeupTaskDO.setDeadlineTime(nextDeadlineTime.getDeadlineTime());
        makeupTaskDO.setMaxCount(settingDO.getMaxCount());
        makeupTaskDO.setDelayTime(nextDeadlineTime.getDelayTime());
        makeupTaskMapper.insert(makeupTaskDO);
        return makeupTaskDO;
    }

    private List<MakeupTaskMaterialDO> getMakeupTaskMaterialItem(OrderDetailDO orderDetailDO) {
        List<MakeupTaskMaterialDO> aDos = new ArrayList<>();
        Integer itemCount = orderDetailDO.getItemCount();
        Integer lastIndexByOrderId =
            makeupTaskMaterialCmdExe.getBaseMapper().getLastIndexByOrderId(orderDetailDO.getOrderId());
        if (lastIndexByOrderId == null) {
            lastIndexByOrderId = 0;
        }
        // 获取订单明细总数
        int sum = detailCmdExe.getBaseMapper().listByOrderId(orderDetailDO.getOrderId()).stream()
            .mapToInt(OrderDetailDO::getItemCount).sum();
        for (int i = 1; i <= itemCount; i++) {
            MakeupTaskMaterialDO aDo = new MakeupTaskMaterialDO();
            aDo.setOrderId(orderDetailDO.getOrderId());
            aDo.setDeleteFlag(DEL_NO);
            aDo.setStatus(OPEN_YES);
            aDo.setMakeupTaskId(null);
            aDo.setOrderDetailId(orderDetailDO.getId());
            aDo.setOrderNo(orderDetailDO.getOrderNo());
            aDo.setMaterialIndex(lastIndexByOrderId + i);
            aDo.setMaterialSum(sum);
            aDo.setDistributionCode(null);
            aDo.setDistributionCodeIndex(null);
            aDos.add(aDo);
        }
        return aDos;
    }

    /**
     * 保存 订单 物料关联关系
     * 
     */
    private void insertMakeupTaskMaterial(List<MakeupTaskMaterialDO> taskDO, Integer makeupTaskId, Integer productId) {
        MakeupTaskDO makeupTaskDO = makeupTaskMapper.selectById(makeupTaskId);
        List<MakeupTaskMaterialDO> materials =
            makeupTaskMaterialCmdExe.getBaseMapper().listByMakeupTaskId(makeupTaskId);
        String distributionNo = getDistributionCode();
        if (materials.size() + taskDO.size() <= makeupTaskDO.getMaxCount()) {
            // 如果待插入的物料 加上 现有的物料 小于等于上限 直接批量插入
            int count = taskDO.size();
            getFirstArr(taskDO, makeupTaskId, distributionNo, count);
            if (materials.size() + taskDO.size() == makeupTaskDO.getMaxCount()) {
                // 拼版已满 改为 拼版完成
                makeupTaskDO.setStatus(MakeupTaskStatus.MAKEUP_COMPLETE.getValue());
                makeupTaskMapper.updateById(makeupTaskDO);
            }
        } else {
            // 相反 集合拆分 第一部分插入现有拼版任务 后面插入其他任务 或者新开任务
            int count = makeupTaskDO.getMaxCount() - materials.size();
            List<MakeupTaskMaterialDO> newArr = getFirstArr(taskDO, makeupTaskId, distributionNo, count);
            // 当前拼版肯定满了
            makeupTaskDO.setStatus(MakeupTaskStatus.MAKEUP_COMPLETE.getValue());
            makeupTaskMapper.updateById(makeupTaskDO);
            taskDO.removeAll(newArr);
            // 与 108行 意义一致
            MakeupTaskProductDO taskProduct = makeupTaskProductCmdExe.getBaseMapper()
                .getByStatusAndProductId(MakeupTaskStatus.WAIT_MAKEUP.getValue(), productId);
            if (taskProduct != null) {
                insertMakeupTaskMaterial(taskDO, taskProduct.getMakeupTaskId(), productId);
            } else {
                MakeupTaskMaterialDO makeupTaskMaterialDO = taskDO.get(0);
                Integer orderId = makeupTaskMaterialDO.getOrderId();
                newCreateMakeupTask(orderId, taskDO, productId);
            }
        }
    }

    /**
     * 获取配货码 保证30天内不重复
     * 
     * @return
     */
    private String getDistributionCode() {
        String distributionNo = NoUtils.getDistributionNo();
        List<MakeupTaskMaterialDO> materialDOS =
            makeupTaskMaterialCmdExe.getBaseMapper().listByDistributionCode(distributionNo);
        if (materialDOS.size() != 0) {
            // 判断重复编码 是不是30天之前的
            Date updated = materialDOS.get(0).getUpdatedAt();
            LocalDateTime updatedDateTime = TimeUtils.dateToLocalDateTime(updated);
            LocalDateTime minus = LocalDateTime.now().minus(1, ChronoUnit.MONTHS);
            if (updatedDateTime.isBefore(minus)) {
                // 30天之前的 伪删除掉
                materialDOS.forEach(makeupTaskMaterialDO -> makeupTaskMaterialDO.setDeleteFlag(DEL_YES));
                makeupTaskMaterialCmdExe.updateBatchById(materialDOS);
                return distributionNo;
            } else {
                return getDistributionCode();
            }
        }
        return distributionNo;
    }

    /**
     * 获取第一部分 集合
     * 
     * @param taskDO
     * @param makeupTaskId
     * @param distributionNo
     * @param count
     * @return
     */
    private List<MakeupTaskMaterialDO> getFirstArr(List<MakeupTaskMaterialDO> taskDO, Integer makeupTaskId,
        String distributionNo, int count) {
        List<MakeupTaskMaterialDO> newArr = new ArrayList();
        for (int i = 0; i < count; i++) {
            MakeupTaskMaterialDO makeupTaskMaterialDO = taskDO.get(i);
            makeupTaskMaterialDO.setMakeupTaskId(makeupTaskId);
            makeupTaskMaterialDO.setDistributionCode(distributionNo);
            if (count == 1) {
                makeupTaskMaterialDO.setDistributionCodeIndex(i);
            } else {
                makeupTaskMaterialDO.setDistributionCodeIndex(i + 1);
            }
            newArr.add(makeupTaskMaterialDO);
        }
        makeupTaskMaterialCmdExe.saveBatch(newArr);
        return newArr;
    }

    @Resource
    private PrintService printService;

    /**
     * 订单拼版
     *
     * @param orderId
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void makeupOrder(Integer orderId) {
        try {
            log.info("订单：{} 开始进入拼版", orderId);
            OrderInfoDO orderInfoDO = orderInfoMapper.selectById(orderId);
            // 待生产
            if (orderInfoDO != null && orderInfoDO.getStatus().equals(OrderStatus.WAIT_PRODUCED.getValue())) {
                List<OrderDetailDO> orderDetails = detailCmdExe.getBaseMapper().listByOrderId(orderId);
                // 订单所有明细 均处于拼版中
                boolean b = orderDetails.stream().allMatch(
                    orderDetailDO -> orderDetailDO.getStatus().equals(OrderDetailStatus.IN_MAKEUP.getValue()));
                if (b) {
                    orderDetails.forEach(orderDetailDO -> {
                        // 后面丢参数了
                        orderDetailDO.setOrderNo(orderInfoDO.getOrderNo());
                        // A1查询现有的未截稿的任务的绑定产品 如果有直接绑定
                        ProductDO byCode = productMapper.getByCode(orderDetailDO.getProductCode());
                        MakeupTaskProductDO taskProduct = makeupTaskProductCmdExe.getBaseMapper()
                            .getByStatusAndProductId(MakeupTaskStatus.WAIT_MAKEUP.getValue(), byCode.getId());
                        List<MakeupTaskMaterialDO> makeupTaskMaterialItem = getMakeupTaskMaterialItem(orderDetailDO);
                        if (taskProduct != null) {
                            insertMakeupTaskMaterial(makeupTaskMaterialItem, taskProduct.getMakeupTaskId(),
                                byCode.getId());
                        } else {
                            newCreateMakeupTask(orderId, makeupTaskMaterialItem, byCode.getId());
                        }
                    });
                }
            } else {
                log.error("订单：{} 不满足拼版条件，json {}", orderId, JSON.toJSONString(orderInfoDO));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
            throw e;
        }
    }

    /**
     * 上方为拼版逻辑
     * =====================================================================================================================
     * 下方为截稿逻辑
     */

    @Resource
    private ProcessorHolder holder;

    /**
     * 截稿
     *
     * @param makeupTaskId
     */
    @Transactional(rollbackFor = Exception.class)
    public void doDeadLine(Integer makeupTaskId) {
        String tpuPdfUrl = "C:\\Users\\lx\\Downloads\\Documents\\dSmctC_1633848905657.pdf";
        String blkPdfUrl = "C:\\Users\\lx\\Downloads\\Documents\\4nJBEQ_1628031936300.pdf";
        String rshPdfUrl = "C:\\Users\\lx\\Downloads\\Documents\\QYYiFE_1634537318935.pdf";
        List<BusinessInfoReq> infoReqs = new ArrayList<>();
        for (int i = 0; i < makeupTaskId; i++) {
            BusinessInfoReq req = new BusinessInfoReq();
            req.setPdfUrl(rshPdfUrl);
            req.setOrderStr(i + "");
            req.setSpecStr("Samsung Galaxy Note Fan Edition");
            req.setQrStr("test");
            req.setSort(i);
            infoReqs.add(req);
        }
        holder.getProcessor("rsh8").makeup(infoReqs);

        // MakeupTaskDO makeupTaskDO = makeupTaskMapper.selectById(makeupTaskId);
        // if (makeupTaskDO == null) {
        // throw LaoYinException.buildException(B_MAKEUP_TASK_NULL, B_MAKEUP_TASK_NULL_MSG);
        // }
        // // if (!makeupTaskDO.getStatus().equals(MakeupTaskStatus.MAKEUP_COMPLETE.getValue())) {
        // // throw LaoYinException.buildException(B_MAKEUP_TASK_STATUS_COMPLETE, B_MAKEUP_TASK_STATUS_COMPLETE_MSG);
        // // }
        // // 生成领料单
        // log.info("异步生成领料单");
        // MakeupTaskDO taskDO = makeupTaskMapper.selectById(makeupTaskId);
        // printService.getMaterialRequisition(taskDO.getCode());
        // // 生成配货标签
        // log.info("异步生成配货标签");
        // printService.getDistributionCodeLabel(taskDO.getCode());
        // try {
        // // TODO 文件拼版
        // PdfWriter writer = new PdfWriter("./a.pdf");
        // PdfDocument pdf = new PdfDocument(writer);
        // Document document = new Document(pdf);
        // document.add(new Paragraph("Hello World!"));
        // document.close();
        // log.info("截稿成功");
        // makeupTaskDO.setStatus(MakeupTaskStatus.DO_DEAD_LINE_SUCCESS.getValue());
        // List<OrderInfoDO> orderDos = changeStatus(makeupTaskId);
        // // 生成发货清单数据 同一个拼版任务中
        // for (int i = 0; i < orderDos.size(); i++) {
        // OrderInfoDO orderInfoDO = orderDos.get(i);
        // // 获取订单下所有的明细 按照配送方式拆分发货单 就算在不同的拼版任务中，也参与生产发货单
        // List<OrderDetailDO> detailDOS = detailCmdExe.getBaseMapper().listByOrderId(orderInfoDO.getId());
        // OrderDeliverDO deliverDO = orderDeliverMapper.getByOrderId(orderInfoDO.getId());
        // // 按照配送方式id 分组
        // Map<Integer, List<OrderDetailDO>> collect =
        // detailDOS.stream().collect(Collectors.groupingBy(OrderDetailDO::getDistributionModeId));
        // for (Map.Entry<Integer, List<OrderDetailDO>> listEntry : collect.entrySet()) {
        // DispatchListDO dispatchListDO = new DispatchListDO();
        // Integer distributionModeId = listEntry.getKey();
        // List<OrderDetailDO> orderDetails = listEntry.getValue();
        // List<Integer> orderDetailIds =
        // orderDetails.stream().map(OrderDetailDO::getId).collect(Collectors.toList());
        // List<MakeupTaskMaterialDO> materialDOS =
        // makeupTaskMaterialCmdExe.getBaseMapper().listByOrderDetailIds(orderDetailIds);
        // BeanUtils.copyProperties(deliverDO, dispatchListDO);
        // dispatchListDO.setOrderDeliverId(deliverDO.getId());
        // dispatchListDO.setDistributionModeId(distributionModeId);
        // dispatchListDO.setCode(NoUtils.getDispatchListNo());
        // dispatchListDO.setProductSum(materialDOS.size());
        // dispatchListMapper.insert(dispatchListDO);
        // List<DispatchListOrderDetailDO> listOrderDetailDOS = new ArrayList<>();
        // orderDetails.forEach(orderDetailDO -> {
        // List<MakeupTaskMaterialDO> taskMaterialDOS =
        // makeupTaskMaterialCmdExe.getBaseMapper()
        // .listByOrderDetailIdGroupByDistributionCode(orderDetailDO.getId());
        // taskMaterialDOS.forEach(makeupTaskMaterialDO -> {
        // DispatchListOrderDetailDO dispatchListOrderDetailDO = new DispatchListOrderDetailDO();
        // dispatchListOrderDetailDO.setTenantId(dispatchListDO.getTenantId());
        // dispatchListOrderDetailDO.setDispatchListId(dispatchListDO.getId());
        // dispatchListOrderDetailDO.setOrderId(orderInfoDO.getId());
        // dispatchListOrderDetailDO.setOrderNo(orderInfoDO.getOrderNo());
        // dispatchListOrderDetailDO.setOrderDetailId(orderDetailDO.getId());
        // dispatchListOrderDetailDO.setDistributionCode(makeupTaskMaterialDO.getDistributionCode());
        // listOrderDetailDOS.add(dispatchListOrderDetailDO);
        // });
        // });
        // dispatchListOrderDetailCmdExe.saveBatch(listOrderDetailDOS);
        // }
        // }
        // } catch (IOException e) {
        // log.error("截稿失败");
        // e.printStackTrace();
        // log.error(e.getLocalizedMessage());
        // makeupTaskDO.setStatus(MakeupTaskStatus.DO_DEAD_LINE_FAIL.getValue());
        // }
        // makeupTaskMapper.updateById(makeupTaskDO);
    }

    /**
     * 订单明细的状态 以及订单的状态 订单待生产->生产中 订单明细 拼版中->拼版完成
     *
     * @param makeupTaskId
     * @return
     */
    private List<OrderInfoDO> changeStatus(Integer makeupTaskId) {
        List<Integer> orderIds =
            makeupTaskMaterialCmdExe.getBaseMapper().listOrderIdByMakeupTaskIdGroupByOrderId(makeupTaskId);
        List<Integer> orderDetailIds =
            makeupTaskMaterialCmdExe.getBaseMapper().listOrderDetailIdByMakeupTaskIdGroupByOrderDetailId(makeupTaskId);
        List<OrderInfoDO> orderInfos = orderInfoMapper.selectBatchIds(orderIds);
        orderInfos.forEach(orderInfoDO -> {
            orderInfoDO.setStatus(OrderStatus.PRODUCING.getValue());
            orderInfoMapper.updateById(orderInfoDO);
        });
        List<OrderDetailDO> orderDetails = detailCmdExe.getBaseMapper().selectBatchIds(orderDetailIds);
        orderDetails.forEach(orderDetailDO -> orderDetailDO.setStatus(OrderDetailStatus.MAKEUP_COMPLETE.getValue()));
        detailCmdExe.updateBatchById(orderDetails);
        return null;
    }

}