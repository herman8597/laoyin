package com.bat.laoyin.service.common.tenant;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.tenant.TenantService;
import com.bat.laoyin.api.tenant.dto.TenantQry;
import com.bat.laoyin.dao.tenant.TenantMapper;
import com.bat.laoyin.dao.tenant.dataobject.TenantDO;
import com.bat.laoyin.service.tenant.executor.TenantCmdExe;
import com.bat.laoyin.service.tenant.executor.TenantQryExe;

/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-08
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, TenantDO> implements TenantService {

    @Resource
    private TenantCmdExe cmdExe;

    @Resource
    private TenantQryExe qryExe;

    public TenantServiceImpl(@Autowired TenantCmdExe serviceCmd, @Autowired TenantQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<TenantDO> selectPageVo(IPage<TenantDO> page, TenantQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<TenantDO> selectVo(TenantQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void mySave(TenantDO aDo) {
        cmdExe.mySave(aDo);
    }

}