package com.bat.laoyin.service.product.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.product.dto.ProductQry;
import com.bat.laoyin.api.product.dto.data.ProductDTO;
import com.bat.laoyin.dao.product.dataobject.ProductDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class ProductConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static ProductDO toProductDO(Object cmd, Short operationType) {
        ProductDO aDo = new ProductDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static ProductDO toProductDO(ProductQry qry) {
        ProductDO aDo = new ProductDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<ProductDTO> toProductDTOList(List<ProductDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(ProductConvertor::toProductDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static ProductDTO toProductDTO(ProductDO aDo) {
        if (aDo != null) {
            ProductDTO dto = new ProductDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
