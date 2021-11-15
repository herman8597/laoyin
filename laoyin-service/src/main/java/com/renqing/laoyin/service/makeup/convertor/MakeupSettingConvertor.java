package com.bat.laoyin.service.makeup.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.makeup.dto.MakeupSettingQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupSettingDTO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class MakeupSettingConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static MakeupSettingDO toMakeupSettingDO(Object cmd, Short operationType) {
        MakeupSettingDO aDo = new MakeupSettingDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static MakeupSettingDO toMakeupSettingDO(MakeupSettingQry qry) {
        MakeupSettingDO aDo = new MakeupSettingDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<MakeupSettingDTO> toMakeupSettingDTOList(List<MakeupSettingDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(MakeupSettingConvertor::toMakeupSettingDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static MakeupSettingDTO toMakeupSettingDTO(MakeupSettingDO aDo) {
        if (aDo != null) {
            MakeupSettingDTO dto = new MakeupSettingDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
