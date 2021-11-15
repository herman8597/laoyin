package com.bat.laoyin.service.makeup.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.makeup.dto.MakeupTaskMaterialQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupTaskMaterialDTO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskMaterialDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class MakeupTaskMaterialConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static MakeupTaskMaterialDO toMakeupTaskMaterialDO(Object cmd, Short operationType) {
        MakeupTaskMaterialDO aDo = new MakeupTaskMaterialDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static MakeupTaskMaterialDO toMakeupTaskMaterialDO(MakeupTaskMaterialQry qry) {
        MakeupTaskMaterialDO aDo = new MakeupTaskMaterialDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<MakeupTaskMaterialDTO> toMakeupTaskMaterialDTOList(List<MakeupTaskMaterialDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(MakeupTaskMaterialConvertor::toMakeupTaskMaterialDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static MakeupTaskMaterialDTO toMakeupTaskMaterialDTO(MakeupTaskMaterialDO aDo) {
        if (aDo != null) {
            MakeupTaskMaterialDTO dto = new MakeupTaskMaterialDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
