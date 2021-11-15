package com.bat.laoyin.service.product.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.product.dto.ProductSpecQry;
import com.bat.laoyin.api.product.dto.data.ProductSpecDTO;
import com.bat.laoyin.dao.product.dataobject.ProductSpecDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class ProductSpecConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static ProductSpecDO toProductSpecDO(Object cmd, Short operationType) {
        ProductSpecDO aDo = new ProductSpecDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static ProductSpecDO toProductSpecDO(ProductSpecQry qry) {
        ProductSpecDO aDo = new ProductSpecDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<ProductSpecDTO> toProductSpecDTOList(List<ProductSpecDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(ProductSpecConvertor::toProductSpecDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static ProductSpecDTO toProductSpecDTO(ProductSpecDO aDo) {
        if (aDo != null) {
            ProductSpecDTO dto = new ProductSpecDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
