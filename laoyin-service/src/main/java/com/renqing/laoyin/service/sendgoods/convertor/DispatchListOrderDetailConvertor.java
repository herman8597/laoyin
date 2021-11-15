package com.bat.laoyin.service.sendgoods.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.sendgoods.dto.DispatchListOrderDetailQry;
import com.bat.laoyin.api.sendgoods.dto.data.DispatchListOrderDetailDTO;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListOrderDetailDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class DispatchListOrderDetailConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static DispatchListOrderDetailDO toDispatchListOrderDetailDO(Object cmd, Short operationType) {
        DispatchListOrderDetailDO aDo = new DispatchListOrderDetailDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static DispatchListOrderDetailDO toDispatchListOrderDetailDO(DispatchListOrderDetailQry qry) {
        DispatchListOrderDetailDO aDo = new DispatchListOrderDetailDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<DispatchListOrderDetailDTO>
        toDispatchListOrderDetailDTOList(List<DispatchListOrderDetailDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(DispatchListOrderDetailConvertor::toDispatchListOrderDetailDTO)
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
    public static DispatchListOrderDetailDTO toDispatchListOrderDetailDTO(DispatchListOrderDetailDO aDo) {
        if (aDo != null) {
            DispatchListOrderDetailDTO dto = new DispatchListOrderDetailDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
