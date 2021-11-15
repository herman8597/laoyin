package com.bat.laoyin.service.sendgoods.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.sendgoods.dto.DispatchListQry;
import com.bat.laoyin.api.sendgoods.dto.data.DispatchListDTO;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListDO;
import com.bat.laoyin.service.common.constants.Constant;
/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class DispatchListConvertor {

    /**
     * CMD 转 DO
     * @param cmd
     * @param operationType
     * @return
     */
    public static DispatchListDO toDispatchListDO(Object cmd, Short operationType) {
        DispatchListDO aDo = new DispatchListDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static DispatchListDO toDispatchListDO(DispatchListQry qry) {
        DispatchListDO aDo = new DispatchListDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<DispatchListDTO> toDispatchListDTOList(List<DispatchListDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(DispatchListConvertor::toDispatchListDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static DispatchListDTO toDispatchListDTO(DispatchListDO aDo) {
        if (aDo != null) {
            DispatchListDTO dto = new DispatchListDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
