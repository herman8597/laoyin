package com.bat.laoyin.service.makeup.executor;

import static com.bat.laoyin.api.common.ErrorCode.B_MAKEUP_TASK_STATUS_NOT_DEAD_LINE_SUCCESS;
import static com.bat.laoyin.api.common.ErrorCode.B_MAKEUP_TASK_STATUS_NOT_DEAD_LINE_SUCCESS_MSG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.common.LaoYinException;
import com.bat.laoyin.api.makeup.dto.MakeupTaskQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupTaskDTO;
import com.bat.laoyin.api.makeup.dto.data.MaterialRequisitionItemRespDTO;
import com.bat.laoyin.api.makeup.dto.data.MaterialRequisitionRespDTO;
import com.bat.laoyin.dao.makeup.MakeupTaskMapper;
import com.bat.laoyin.dao.makeup.MakeupTaskMaterialMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskDO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskMaterialDO;
import com.bat.laoyin.dao.makeup.dataobject.MyPickListItemDO;
import com.bat.laoyin.dao.order.OrderDetailMapper;
import com.bat.laoyin.service.common.enums.MakeupTaskStatus;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.makeup.convertor.MakeupTaskConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class MakeupTaskQryExe extends ServiceQryImpl<MakeupTaskMapper, MakeupTaskDO> {

    @Resource
    private MakeupTaskMapper mapper;

    @Resource
    private MakeupTaskMaterialMapper MakeupTaskMaterialMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    public IPage<MakeupTaskDTO> selectPageVo(IPage<MakeupTaskDO> page, MakeupTaskQry qry) {
        MakeupTaskDO aDo = MakeupTaskConvertor.toMakeupTaskDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        Map<String, Object> map = new HashMap<>();
        map.put("makeupStartTime", qry.getMakeupStartTime());
        map.put("makeupEndTime", qry.getMakeupEndTime());
        IPage iPage = mapper.selectPageVo(page, aDo, sort, map);
        List<MakeupTaskDO> records = iPage.getRecords();
        List<MakeupTaskDTO> collect = records.stream().map(o -> {
            MakeupTaskDTO makeupTaskDTO = MakeupTaskConvertor.toMakeupTaskDTO(o);
            List<MakeupTaskMaterialDO> orderDetailDOS =
                MakeupTaskMaterialMapper.listByMakeupTaskId(makeupTaskDTO.getId());
            List<String> orderNos =
                orderDetailDOS.stream().map(MakeupTaskMaterialDO::getOrderNo).distinct().collect(Collectors.toList());
            makeupTaskDTO.setOrderNos(orderNos);
            return makeupTaskDTO;
        }).collect(Collectors.toList());
        iPage.setRecords(collect);
        return iPage;
    }

    public List<MakeupTaskDO> selectVo(MakeupTaskQry qry) {
        MakeupTaskDO aDo = MakeupTaskConvertor.toMakeupTaskDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }

    /**
     * 获取领料单
     *
     * @param makeupTaskCode
     * @return
     */
    public MaterialRequisitionRespDTO getMaterialRequisition(String makeupTaskCode) {
        MakeupTaskDO makeupTaskDO = mapper.getByCode(makeupTaskCode);
        if (!makeupTaskDO.getStatus().equals(MakeupTaskStatus.DO_DEAD_LINE_SUCCESS.getValue())) {
            throw LaoYinException.buildException(B_MAKEUP_TASK_STATUS_NOT_DEAD_LINE_SUCCESS,
                B_MAKEUP_TASK_STATUS_NOT_DEAD_LINE_SUCCESS_MSG);
        }
        MaterialRequisitionRespDTO dto = new MaterialRequisitionRespDTO();
        dto.setMakeupTaskCode(makeupTaskDO.getCode());
        List<MyPickListItemDO> materialRequisition =
            MakeupTaskMaterialMapper.getMaterialRequisition(makeupTaskDO.getId());
        List<MaterialRequisitionItemRespDTO> pickListItemDTOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(materialRequisition)) {
            pickListItemDTOS = materialRequisition.stream().map(myPickListItemDO -> {
                MaterialRequisitionItemRespDTO itemDTO = new MaterialRequisitionItemRespDTO();
                BeanUtils.copyProperties(myPickListItemDO, itemDTO);
                return itemDTO;
            }).collect(Collectors.toList());
        }
        dto.setMaterialRequisitionItems(pickListItemDTOS);
        return dto;
    }
}
