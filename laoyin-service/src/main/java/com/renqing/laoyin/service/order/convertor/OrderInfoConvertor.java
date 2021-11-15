package com.bat.laoyin.service.order.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.order.dto.OrderInfoQry;
import com.bat.laoyin.api.order.dto.data.OrderInfoDTO;
import com.bat.laoyin.api.third.dto.OrderInfoCreateCmd;
import com.bat.laoyin.dao.order.dataobject.OrderInfoDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class OrderInfoConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static OrderInfoDO toOrderInfoDO(Object cmd, Short operationType) {
        OrderInfoDO aDo = new OrderInfoDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static OrderInfoDO toOrderInfoDO(OrderInfoQry qry) {
        OrderInfoDO aDo = new OrderInfoDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<OrderInfoDTO> toOrderInfoDTOList(List<OrderInfoCreateCmd> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(OrderInfoConvertor::toOrderInfoDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static OrderInfoDTO toOrderInfoDTO(OrderInfoCreateCmd aDo) {
        if (aDo != null) {
            OrderInfoDTO dto = new OrderInfoDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
