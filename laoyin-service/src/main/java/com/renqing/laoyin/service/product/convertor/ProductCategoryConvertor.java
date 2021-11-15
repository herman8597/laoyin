package com.bat.laoyin.service.product.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.product.dto.ProductCategoryQry;
import com.bat.laoyin.api.product.dto.data.ProductCategoryDTO;
import com.bat.laoyin.dao.product.dataobject.ProductCategoryDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class ProductCategoryConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static ProductCategoryDO toProductCategoryDO(Object cmd, Short operationType) {
        ProductCategoryDO aDo = new ProductCategoryDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static ProductCategoryDO toProductCategoryDO(ProductCategoryQry qry) {
        ProductCategoryDO aDo = new ProductCategoryDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<ProductCategoryDTO> toProductCategoryDTOList(List<ProductCategoryDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(ProductCategoryConvertor::toProductCategoryDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static ProductCategoryDTO toProductCategoryDTO(ProductCategoryDO aDo) {
        if (aDo != null) {
            ProductCategoryDTO dto = new ProductCategoryDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
