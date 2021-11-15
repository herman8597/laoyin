package com.bat.laoyin.service.product.executor;

import javax.annotation.Resource;

import com.bat.laoyin.dao.product.ProductMapper;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.product.ProductFamilyMapper;
import com.bat.laoyin.dao.product.dataobject.ProductFamilyDO;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class ProductFamilyCmdExe extends ServiceCmdImpl<ProductFamilyMapper, ProductFamilyDO> {

    @Resource
    private ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    public void myUpdateById(ProductFamilyDO oldObj) {
        updateById(oldObj);
        productMapper.updateFamilyNameByFamilyId(oldObj.getId(),oldObj.getName());
    }
}
