package com.bat.laoyin.service.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.product.ProductBrandService;
import com.bat.laoyin.api.product.dto.ProductBrandQry;
import com.bat.laoyin.dao.product.ProductBrandMapper;
import com.bat.laoyin.dao.product.dataobject.ProductBrandDO;
import com.bat.laoyin.service.product.executor.ProductBrandCmdExe;
import com.bat.laoyin.service.product.executor.ProductBrandQryExe;

/**
 * <p>
 * 产品品牌表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
@Service
public class ProductBrandServiceImpl extends ServiceImpl<ProductBrandMapper, ProductBrandDO>
    implements ProductBrandService {

    @Resource
    private ProductBrandCmdExe cmdExe;

    @Resource
    private ProductBrandQryExe qryExe;

    public ProductBrandServiceImpl(@Autowired ProductBrandCmdExe serviceCmd, @Autowired ProductBrandQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<ProductBrandDO> selectPageVo(IPage<ProductBrandDO> page, ProductBrandQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<ProductBrandDO> selectVo(ProductBrandQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void myUpdateById(ProductBrandDO newObj) {
        cmdExe.myUpdateById(newObj);
    }

}