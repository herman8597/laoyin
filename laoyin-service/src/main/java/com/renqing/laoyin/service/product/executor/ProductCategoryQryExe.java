package com.bat.laoyin.service.product.executor;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.product.dto.ProductCategoryQry;
import com.bat.laoyin.dao.product.ProductCategoryMapper;
import com.bat.laoyin.dao.product.dataobject.ProductCategoryDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.product.convertor.ProductCategoryConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class ProductCategoryQryExe extends ServiceQryImpl<ProductCategoryMapper, ProductCategoryDO> {
    @Resource
    private ProductCategoryMapper mapper;

    public IPage<ProductCategoryDO> selectPageVo(IPage<ProductCategoryDO> page, ProductCategoryQry qry) {
        ProductCategoryDO aDo = ProductCategoryConvertor.toProductCategoryDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        IPage<ProductCategoryDO> productCategoryDOIPage = mapper.selectPageVo(page, aDo, sort);
        productCategoryDOIPage.getRecords().forEach(productCategoryDO -> {
            List<ProductCategoryDO> productCategoryDOS = recursionTree(productCategoryDO.getId());
            productCategoryDO.setChildren(productCategoryDOS);
        });
        return productCategoryDOIPage;
    }

    public List<ProductCategoryDO> recursionTree(Integer parentId) {
        ProductCategoryQry qry = new ProductCategoryQry();
        qry.setParentId(parentId);
        List<ProductCategoryDO> productCategoryDOS = selectVo(qry);
        if (!CollectionUtils.isEmpty(productCategoryDOS)) {
            return productCategoryDOS.stream()
                .peek(productCategoryDO -> productCategoryDO.setChildren(recursionTree(productCategoryDO.getId())))
                .collect(Collectors.toList());
        }
        return productCategoryDOS;
    }

    public List<ProductCategoryDO> selectVo(ProductCategoryQry qry) {
        ProductCategoryDO aDo = ProductCategoryConvertor.toProductCategoryDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
