package com.bat.laoyin.service.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.product.ProductSpecService;
import com.bat.laoyin.api.product.dto.ProductSpecCmd;
import com.bat.laoyin.api.product.dto.ProductSpecQry;
import com.bat.laoyin.dao.product.ProductSpecMapper;
import com.bat.laoyin.dao.product.dataobject.ProductSpecDO;
import com.bat.laoyin.service.product.executor.ProductSpecCmdExe;
import com.bat.laoyin.service.product.executor.ProductSpecQryExe;

/**
 * <p>
 * 产品规格表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-25
 */
@Service
public class ProductSpecServiceImpl extends ServiceImpl<ProductSpecMapper, ProductSpecDO>
    implements ProductSpecService {

    @Resource
    private ProductSpecCmdExe cmdExe;

    @Resource
    private ProductSpecQryExe qryExe;

    public ProductSpecServiceImpl(@Autowired ProductSpecCmdExe serviceCmd, @Autowired ProductSpecQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<ProductSpecDO> selectPageVo(IPage<ProductSpecDO> page, ProductSpecQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<ProductSpecDO> selectVo(ProductSpecQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void mySaveList(ProductSpecCmd aDos) {
        cmdExe.mySaveList(aDos);
    }

    @Override
    public void myUpdateList(ProductSpecCmd aDos) {
        cmdExe.myUpdateList(aDos);
    }

}