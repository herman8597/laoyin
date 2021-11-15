package com.bat.laoyin.api.product.dto;

import java.util.List;

import com.bat.laoyin.dao.product.dataobject.ProductFamilyDO;
import com.bat.laoyin.dao.product.dataobject.ProductSpecDO;

import lombok.Data;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/9 10:01
 */
@Data
public class ProductSpecCmd {
    private ProductFamilyDO familyDO;
    private List<ProductSpecDO> specDOS;
}
