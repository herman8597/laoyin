package com.bat.laoyin.service.product.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.product.dto.ProductBrandQry;
import com.bat.laoyin.dao.product.ProductBrandMapper;
import com.bat.laoyin.dao.product.dataobject.ProductBrandDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.product.convertor.ProductBrandConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class ProductBrandQryExe extends ServiceQryImpl<ProductBrandMapper, ProductBrandDO> {
    @Resource
    private ProductBrandMapper mapper;

    public IPage<ProductBrandDO> selectPageVo(IPage<ProductBrandDO> page, ProductBrandQry qry) {
        ProductBrandDO aDo = ProductBrandConvertor.toProductBrandDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<ProductBrandDO> selectVo(ProductBrandQry qry) {
        ProductBrandDO aDo = ProductBrandConvertor.toProductBrandDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
