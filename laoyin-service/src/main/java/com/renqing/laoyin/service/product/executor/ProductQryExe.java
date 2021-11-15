package com.bat.laoyin.service.product.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.product.dto.ProductQry;
import com.bat.laoyin.dao.product.ProductMapper;
import com.bat.laoyin.dao.product.dataobject.ProductDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.product.convertor.ProductConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class ProductQryExe extends ServiceQryImpl<ProductMapper, ProductDO> {
    @Resource
    private ProductMapper mapper;

    public IPage<ProductDO> selectPageVo(IPage<ProductDO> page, ProductQry qry) {
        ProductDO aDo = ProductConvertor.toProductDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<ProductDO> selectVo(ProductQry qry) {
        ProductDO aDo = ProductConvertor.toProductDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
