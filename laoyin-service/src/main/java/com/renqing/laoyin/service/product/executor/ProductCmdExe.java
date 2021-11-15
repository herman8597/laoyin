package com.bat.laoyin.service.product.executor;

import static com.bat.laoyin.api.common.ErrorCode.*;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.api.common.LaoYinException;
import com.bat.laoyin.dao.product.*;
import com.bat.laoyin.dao.product.dataobject.*;
import com.bat.laoyin.service.common.utils.NoUtils;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class ProductCmdExe extends ServiceCmdImpl<ProductMapper, ProductDO> {

    @Resource
    private ProductFamilyMapper familyMapper;

    @Resource
    private ProductCategoryMapper categoryMapper;

    @Resource
    private ProductBrandMapper brandMapper;

    @Resource
    private ProductSpecMapper specMapper;

    /**
     * 使用MP的批量插入操作（还是推荐）
     * 
     * mybatis-plus的IService里面的batch方法是使用了batchSession进行提交的，只要你的jdbc连接上加上了rewriteBatchedStatements=true配置项，<br/>
     * 在调用batch*方法的时候，实际上就是开启了批量提交，但是是在批量的条数完成后一次性提交过去的，虽然还是一条一条插入，但是最终是一个提交请求完成的，<br/>
     * 这个和for循环，提交一条开启一个事物的方式是完全不同的
     * 
     * @param aDos
     */
    @Transactional(rollbackFor = Exception.class)
    public void mySaveList(List<ProductDO> aDos) {
        try {
            aDos.forEach(productDO -> {
                productDO.setCode(NoUtils.getProductNo());
                fillInfo(productDO);
            });
            myInsertList(aDos);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private void fillInfo(ProductDO productDO) {
        ProductFamilyDO productFamilyDO = familyMapper.selectById(productDO.getProductFamilyId());
        if (productFamilyDO == null) {
            throw LaoYinException.buildException(B_PRODUCT_FAMILY_NULL, B_PRODUCT_FAMILY_NULL_MSG);
        }
        productDO.setProductCategoryId(productFamilyDO.getProductCategoryId());
        ProductCategoryDO productCategoryDO = categoryMapper.selectById(productFamilyDO.getProductCategoryId());
        if (productCategoryDO == null) {
            throw LaoYinException.buildException(B_PRODUCT_CATEGORY_NULL, B_PRODUCT_CATEGORY_NULL_MSG);
        }
        productDO.setProductCategoryName(productCategoryDO.getName());
        ProductBrandDO productBrandDO = brandMapper.selectById(productDO.getProductBrandId());
        if (productBrandDO == null) {
            throw LaoYinException.buildException(B_PRODUCT_BRAND_NULL, B_PRODUCT_BRAND_NULL_MSG);
        }
        productDO.setProductBrandName(productBrandDO.getName());
        productDO.setProductFamilyName(productFamilyDO.getName());
        ProductSpecDO productSpecDO = specMapper.selectById(productDO.getProductSpecId());
        if (productSpecDO == null) {
            throw LaoYinException.buildException(B_PRODUCT_SPEC_NULL, B_PRODUCT_SPEC_NULL_MSG);
        }
        productDO.setProductSpecName(productSpecDO.getSimpleSpecName());
    }

    private void myInsertList(List<ProductDO> collect) {
        try {
            saveBatch(collect);
        } catch (DuplicateKeyException e) {
            final String localizedMessage = e.getLocalizedMessage();
            if (localizedMessage.contains("Duplicate entry") && localizedMessage.contains("uk_code")) {
                mySaveList(collect);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void myUpdateById(ProductDO newObj) {
        try {
            fillInfo(newObj);
            updateById(newObj);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
