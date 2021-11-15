package com.bat.laoyin.service.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.product.ProductService;
import com.bat.laoyin.api.product.dto.ProductQry;
import com.bat.laoyin.api.product.dto.data.ProductDTO;
import com.bat.laoyin.dao.product.ProductMapper;
import com.bat.laoyin.dao.product.dataobject.ProductDO;
import com.bat.laoyin.service.product.executor.ProductCmdExe;
import com.bat.laoyin.service.product.executor.ProductQryExe;

/**
 * <p>
 * 产品表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-30
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements ProductService {

    @Resource
    private ProductCmdExe cmdExe;

    @Resource
    private ProductQryExe qryExe;

    public ProductServiceImpl(@Autowired ProductCmdExe serviceCmd, @Autowired ProductQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<ProductDO> selectPageVo(IPage<ProductDO> page, ProductQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<ProductDO> selectVo(ProductQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void mySaveList(List<ProductDO> aDos) {
        cmdExe.mySaveList(aDos);
    }

    @Override
    public void myUpdateById(ProductDO newObj) {
        cmdExe.myUpdateById(newObj);
    }

}