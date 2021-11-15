package com.bat.laoyin.service.product.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.product.dto.ProductFamilyQry;
import com.bat.laoyin.api.product.dto.data.ProductFamilyDTO;
import com.bat.laoyin.dao.product.dataobject.ProductFamilyDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class ProductFamilyConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static ProductFamilyDO toProductFamilyDO(Object cmd, Short operationType) {
        ProductFamilyDO aDo = new ProductFamilyDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static ProductFamilyDO toProductFamilyDO(ProductFamilyQry qry) {
        ProductFamilyDO aDo = new ProductFamilyDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<ProductFamilyDTO> toProductFamilyDTOList(List<ProductFamilyDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(ProductFamilyConvertor::toProductFamilyDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static ProductFamilyDTO toProductFamilyDTO(ProductFamilyDO aDo) {
        if (aDo != null) {
            ProductFamilyDTO dto = new ProductFamilyDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
