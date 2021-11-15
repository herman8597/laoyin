package com.bat.laoyin.service.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.product.ProductCategoryService;
import com.bat.laoyin.api.product.dto.ProductCategoryQry;
import com.bat.laoyin.dao.product.ProductCategoryMapper;
import com.bat.laoyin.dao.product.dataobject.ProductCategoryDO;
import com.bat.laoyin.service.product.executor.ProductCategoryCmdExe;
import com.bat.laoyin.service.product.executor.ProductCategoryQryExe;

/**
 * <p>
 * 产品类别表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategoryDO>
    implements ProductCategoryService {

    @Resource
    private ProductCategoryCmdExe cmdExe;

    @Resource
    private ProductCategoryQryExe qryExe;

    public ProductCategoryServiceImpl(@Autowired ProductCategoryCmdExe serviceCmd,
        @Autowired ProductCategoryQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<ProductCategoryDO> selectPageVo(IPage<ProductCategoryDO> page, ProductCategoryQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<ProductCategoryDO> selectVo(ProductCategoryQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void myUpdateById(ProductCategoryDO newObj) {
        cmdExe.myUpdateById(newObj);
    }

}