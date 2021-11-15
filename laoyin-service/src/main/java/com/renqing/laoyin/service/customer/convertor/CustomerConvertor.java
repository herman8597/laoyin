package com.bat.laoyin.service.customer.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.customer.dto.CustomerQry;
import com.bat.laoyin.api.customer.dto.data.CustomerDTO;
import com.bat.laoyin.dao.customer.dataobject.CustomerDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class CustomerConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static CustomerDO toCustomerDO(Object cmd, Short operationType) {
        CustomerDO aDo = new CustomerDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static CustomerDO toCustomerDO(CustomerQry qry) {
        CustomerDO aDo = new CustomerDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<CustomerDTO> toCustomerDTOList(List<CustomerDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(CustomerConvertor::toCustomerDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static CustomerDTO toCustomerDTO(CustomerDO aDo) {
        if (aDo != null) {
            CustomerDTO dto = new CustomerDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
