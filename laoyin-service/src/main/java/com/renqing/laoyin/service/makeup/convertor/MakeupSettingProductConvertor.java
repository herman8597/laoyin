package com.bat.laoyin.service.makeup.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.makeup.dto.MakeupSettingProductQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupSettingProductDTO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingProductDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class MakeupSettingProductConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static MakeupSettingProductDO toMakeupSettingProductDO(Object cmd, Short operationType) {
        MakeupSettingProductDO aDo = new MakeupSettingProductDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static MakeupSettingProductDO toMakeupSettingProductDO(MakeupSettingProductQry qry) {
        MakeupSettingProductDO aDo = new MakeupSettingProductDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<MakeupSettingProductDTO> toMakeupSettingProductDTOList(List<MakeupSettingProductDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(MakeupSettingProductConvertor::toMakeupSettingProductDTO)
                .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static MakeupSettingProductDTO toMakeupSettingProductDTO(MakeupSettingProductDO aDo) {
        if (aDo != null) {
            MakeupSettingProductDTO dto = new MakeupSettingProductDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
