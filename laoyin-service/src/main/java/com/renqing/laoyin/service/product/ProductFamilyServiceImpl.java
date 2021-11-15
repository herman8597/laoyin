package com.bat.laoyin.service.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.product.ProductFamilyService;
import com.bat.laoyin.api.product.dto.ProductFamilyQry;
import com.bat.laoyin.api.product.dto.data.ProductFamilyDTO;
import com.bat.laoyin.dao.product.ProductFamilyMapper;
import com.bat.laoyin.dao.product.dataobject.ProductFamilyDO;
import com.bat.laoyin.service.product.executor.ProductFamilyCmdExe;
import com.bat.laoyin.service.product.executor.ProductFamilyQryExe;

/**
 * <p>
 * 产品品类(产品簇)表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-25
 */
@Service
public class ProductFamilyServiceImpl extends ServiceImpl<ProductFamilyMapper, ProductFamilyDO>
    implements ProductFamilyService {

    @Resource
    private ProductFamilyCmdExe cmdExe;

    @Resource
    private ProductFamilyQryExe qryExe;

    public ProductFamilyServiceImpl(@Autowired ProductFamilyCmdExe serviceCmd,
        @Autowired ProductFamilyQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<ProductFamilyDTO> selectPageVo(IPage<ProductFamilyDO> page, ProductFamilyQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<ProductFamilyDO> selectVo(ProductFamilyQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void myUpdateById(ProductFamilyDO newObj) {
        cmdExe.myUpdateById(newObj);
    }

}