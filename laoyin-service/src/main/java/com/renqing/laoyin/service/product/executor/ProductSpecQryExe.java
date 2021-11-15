package com.bat.laoyin.service.product.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.product.dto.ProductSpecQry;
import com.bat.laoyin.dao.product.ProductSpecMapper;
import com.bat.laoyin.dao.product.dataobject.ProductSpecDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.product.convertor.ProductSpecConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class ProductSpecQryExe extends ServiceQryImpl<ProductSpecMapper, ProductSpecDO> {

    @Resource
    private ProductSpecMapper mapper;

    public IPage<ProductSpecDO> selectPageVo(IPage<ProductSpecDO> page, ProductSpecQry qry) {
        ProductSpecDO aDo = ProductSpecConvertor.toProductSpecDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<ProductSpecDO> selectVo(ProductSpecQry qry) {
        ProductSpecDO aDo = ProductSpecConvertor.toProductSpecDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
