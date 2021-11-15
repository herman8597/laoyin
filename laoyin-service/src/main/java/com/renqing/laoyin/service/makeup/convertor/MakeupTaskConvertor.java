package com.bat.laoyin.service.makeup.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.makeup.dto.MakeupTaskQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupTaskDTO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class MakeupTaskConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static MakeupTaskDO toMakeupTaskDO(Object cmd, Short operationType) {
        MakeupTaskDO aDo = new MakeupTaskDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static MakeupTaskDO toMakeupTaskDO(MakeupTaskQry qry) {
        MakeupTaskDO aDo = new MakeupTaskDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<MakeupTaskDTO> toMakeupTaskDTOList(List<MakeupTaskDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(MakeupTaskConvertor::toMakeupTaskDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static MakeupTaskDTO toMakeupTaskDTO(MakeupTaskDO aDo) {
        if (aDo != null) {
            MakeupTaskDTO dto = new MakeupTaskDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
