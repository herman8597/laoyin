package com.bat.laoyin.service.order.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.order.dto.OrderDetailQry;
import com.bat.laoyin.api.order.dto.data.OrderDetailDTO;
import com.bat.laoyin.dao.order.dataobject.OrderDetailDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class OrderDetailConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static OrderDetailDO toOrderDetailDO(Object cmd, Short operationType) {
        OrderDetailDO aDo = new OrderDetailDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static OrderDetailDO toOrderDetailDO(OrderDetailQry qry) {
        OrderDetailDO aDo = new OrderDetailDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<OrderDetailDTO> toOrderDetailDTOList(List<OrderDetailDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(OrderDetailConvertor::toOrderDetailDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static OrderDetailDTO toOrderDetailDTO(OrderDetailDO aDo) {
        if (aDo != null) {
            OrderDetailDTO dto = new OrderDetailDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
