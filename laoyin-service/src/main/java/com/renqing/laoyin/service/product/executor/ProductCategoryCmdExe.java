package com.bat.laoyin.service.product.executor;

import com.bat.laoyin.dao.product.ProductMapper;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.product.ProductCategoryMapper;
import com.bat.laoyin.dao.product.dataobject.ProductCategoryDO;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class ProductCategoryCmdExe extends ServiceCmdImpl<ProductCategoryMapper, ProductCategoryDO> {

    @Resource
    private ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    public void myUpdateById(ProductCategoryDO newObj) {
        updateById(newObj);
        productMapper.updateCategoryByCategoryId(newObj.getId(),newObj.getName());
    }
}
