package com.bat.laoyin.service.order.convertor;

import static com.bat.laoyin.service.common.constants.Constant.OPERATION_TYPE_CREATE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.order.dto.OrderDeliverQry;
import com.bat.laoyin.api.order.dto.data.OrderDeliverDTO;
import com.bat.laoyin.dao.order.dataobject.OrderDeliverDO;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class OrderDeliverConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static OrderDeliverDO toOrderDeliverDO(Object cmd, Short operationType) {
        OrderDeliverDO aDo = new OrderDeliverDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static OrderDeliverDO toOrderDeliverDO(OrderDeliverQry qry) {
        OrderDeliverDO aDo = new OrderDeliverDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<OrderDeliverDTO> toOrderDeliverDTOList(List<OrderDeliverDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(OrderDeliverConvertor::toOrderDeliverDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static OrderDeliverDTO toOrderDeliverDTO(OrderDeliverDO aDo) {
        if (aDo != null) {
            OrderDeliverDTO dto = new OrderDeliverDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
