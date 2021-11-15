package com.bat.laoyin.service.makeup.executor;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.common.ErrorCode;
import com.bat.laoyin.api.common.LaoYinException;
import com.bat.laoyin.api.makeup.dto.MakeupTaskMaterialQry;
import com.bat.laoyin.api.makeup.dto.data.DistributionCodeItemRespDTO;
import com.bat.laoyin.api.makeup.dto.data.MakeupTaskMaterialDTO;
import com.bat.laoyin.dao.goods.GoodsMapper;
import com.bat.laoyin.dao.goods.dataobject.GoodsDO;
import com.bat.laoyin.dao.makeup.MakeupTaskMapper;
import com.bat.laoyin.dao.makeup.MakeupTaskMaterialMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskDO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskMaterialDO;
import com.bat.laoyin.dao.order.OrderDetailMapper;
import com.bat.laoyin.dao.order.OrderInfoMapper;
import com.bat.laoyin.dao.order.dataobject.OrderDetailDO;
import com.bat.laoyin.dao.order.dataobject.OrderInfoDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.makeup.convertor.MakeupTaskMaterialConvertor;
import com.bat.laoyin.service.order.convertor.OrderDetailConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class MakeupTaskMaterialQryExe extends ServiceQryImpl<MakeupTaskMaterialMapper, MakeupTaskMaterialDO> {
    @Resource
    private MakeupTaskMaterialMapper mapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private MakeupTaskMapper makeupTaskMapper;

    public IPage<MakeupTaskMaterialDTO> selectPageVo(IPage<MakeupTaskMaterialDO> page, MakeupTaskMaterialQry qry) {
        MakeupTaskMaterialDO aDo = MakeupTaskMaterialConvertor.toMakeupTaskMaterialDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        IPage iPage = mapper.selectPageVo(page, aDo, sort);
        List<MakeupTaskMaterialDO> records = iPage.getRecords();
        List<MakeupTaskMaterialDTO> collect = getMakeupTaskMaterialDTOS(records);
        iPage.setRecords(collect);
        return iPage;
    }

    public List<MakeupTaskMaterialDO> selectVo(MakeupTaskMaterialQry qry) {
        MakeupTaskMaterialDO aDo = MakeupTaskMaterialConvertor.toMakeupTaskMaterialDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }

    public List<MakeupTaskMaterialDTO> listOrderDetail(MakeupTaskMaterialQry qry) {
        List<MakeupTaskMaterialDO> makeupTaskMaterial =
            mapper.listByMakeupTaskIdGroupByOrderDetailId(qry.getMakeupTaskId());
        return getMakeupTaskMaterialDTOS(makeupTaskMaterial);
    }

    /**
     * 封装查询详情
     * 
     * @param records
     * @return
     */
    private List<MakeupTaskMaterialDTO> getMakeupTaskMaterialDTOS(List<MakeupTaskMaterialDO> records) {
        return records.stream().map(MakeupTaskMaterialDO -> {
            MakeupTaskMaterialDTO dto = MakeupTaskMaterialConvertor.toMakeupTaskMaterialDTO(MakeupTaskMaterialDO);
            Integer orderDetailId = MakeupTaskMaterialDO.getOrderDetailId();
            OrderDetailDO detailDO = orderDetailMapper.selectById(orderDetailId);
            dto.setOrderDetail(OrderDetailConvertor.toOrderDetailDTO(detailDO));
            return dto;
        }).collect(Collectors.toList());
    }

    public List<DistributionCodeItemRespDTO> listDistributionCode(String makeupTaskCode) {
        // 拼版任务明细
        MakeupTaskDO byCode = makeupTaskMapper.getByCode(makeupTaskCode);
        if (byCode == null) {
            throw LaoYinException.buildException(ErrorCode.B_MAKEUP_TASK_NULL, ErrorCode.B_MAKEUP_TASK_NULL_MSG);
        }
        List<MakeupTaskMaterialDO> makeupTaskMaterial = getBaseMapper().listByMakeupTaskId(byCode.getId());
        List<DistributionCodeItemRespDTO> collect = makeupTaskMaterial.stream().map(makeupTaskMaterialDO -> {
            DistributionCodeItemRespDTO dto = new DistributionCodeItemRespDTO();
            // 配货编码
            dto.setDistributionCode(makeupTaskMaterialDO.getDistributionCode());
            dto.setDistributionCodeIndex(makeupTaskMaterialDO.getDistributionCodeIndex());
            dto.setMaterialIndex(makeupTaskMaterialDO.getMaterialIndex());
            dto.setMaterialSum(makeupTaskMaterialDO.getMaterialSum());
            Integer orderDetailId = makeupTaskMaterialDO.getOrderDetailId();
            OrderDetailDO orderDetailDO = orderDetailMapper.selectById(orderDetailId);
            if (orderDetailDO != null) {
                dto.setEffectFilePath(orderDetailDO.getEffectFilePath());
                dto.setItemCount(orderDetailDO.getItemCount());
                GoodsDO goodsDO = goodsMapper.selectById(orderDetailDO.getGoodsId());
                if (goodsDO != null) {
                    dto.setGoodsName(goodsDO.getName());
                    dto.setThirdPartySpecName(goodsDO.getSpec());
                    OrderInfoDO orderInfoDO = orderInfoMapper.selectById(orderDetailDO.getOrderId());
                    if (orderInfoDO != null) {
                        dto.setOrderNo(orderInfoDO.getOrderNo());
                        dto.setCustomerName(orderInfoDO.getCustomerName());
                        dto.setThirdPartyNo(orderInfoDO.getThirdPartyNo());
                    }
                }
            }
            return dto;
        }).collect(Collectors.toList());
        return collect;
    }

}
