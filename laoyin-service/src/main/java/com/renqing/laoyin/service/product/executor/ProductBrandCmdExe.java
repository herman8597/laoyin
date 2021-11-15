package com.bat.laoyin.service.product.executor;

import com.bat.laoyin.dao.product.ProductMapper;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.product.ProductBrandMapper;
import com.bat.laoyin.dao.product.dataobject.ProductBrandDO;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class ProductBrandCmdExe extends ServiceCmdImpl<ProductBrandMapper, ProductBrandDO> {

    @Resource
    private ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    public void myUpdateById(ProductBrandDO newObj) {
        updateById(newObj);
        productMapper.updateBrandNameByBrandId(newObj.getId(),newObj.getName());
    }
}
