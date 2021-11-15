package com.bat.laoyin.service.customer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.customer.CustomerService;
import com.bat.laoyin.api.customer.dto.CustomerQry;
import com.bat.laoyin.dao.customer.CustomerMapper;
import com.bat.laoyin.dao.customer.dataobject.CustomerDO;
import com.bat.laoyin.service.customer.executor.CustomerCmdExe;
import com.bat.laoyin.service.customer.executor.CustomerQryExe;

/**
 * <p>
 * 租户客户表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-09-03
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, CustomerDO> implements CustomerService {

    @Resource
    private CustomerCmdExe cmdExe;

    @Resource
    private CustomerQryExe qryExe;

    public CustomerServiceImpl(@Autowired CustomerCmdExe serviceCmd, @Autowired CustomerQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<CustomerDO> selectPageVo(IPage<CustomerDO> page, CustomerQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<CustomerDO> selectVo(CustomerQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void mySave(CustomerDO aDo) {
        cmdExe.mySave(aDo);
    }

    @Override
    public void resetAppSecret(Integer id) {
        cmdExe.resetAppSecret(id);
    }

}