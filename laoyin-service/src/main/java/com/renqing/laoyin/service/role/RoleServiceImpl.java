package com.bat.laoyin.service.common.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.role.RoleService;
import com.bat.laoyin.api.role.dto.RoleQry;
import com.bat.laoyin.dao.role.RoleMapper;
import com.bat.laoyin.dao.role.dataobject.RoleDO;
import com.bat.laoyin.service.common.role.executor.RoleCmdExe;
import com.bat.laoyin.service.common.role.executor.RoleQryExe;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {

    @Resource
    private RoleCmdExe cmdExe;

    @Resource
    private RoleQryExe qryExe;

    public RoleServiceImpl(@Autowired RoleCmdExe serviceCmd, @Autowired RoleQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<RoleDO> selectPageVo(IPage<RoleDO> page, RoleQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<RoleDO> selectVo(RoleQry qry) {
        return qryExe.selectVo(qry);
    }

}