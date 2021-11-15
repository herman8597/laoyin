package com.bat.laoyin.service.common.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.user.UserRoleService;
import com.bat.laoyin.api.user.dto.UserRoleQry;
import com.bat.laoyin.dao.user.UserRoleMapper;
import com.bat.laoyin.dao.user.dataobject.UserRoleDO;
import com.bat.laoyin.service.common.user.executor.UserRoleCmdExe;
import com.bat.laoyin.service.common.user.executor.UserRoleQryExe;

/**
 * <p>
 * 人员角色关联表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-16
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleDO> implements UserRoleService {

    @Resource
    private UserRoleCmdExe cmdExe;

    @Resource
    private UserRoleQryExe qryExe;

    public UserRoleServiceImpl(@Autowired UserRoleCmdExe serviceCmd, @Autowired UserRoleQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<UserRoleDO> selectPageVo(IPage<UserRoleDO> page, UserRoleQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<UserRoleDO> selectVo(UserRoleQry qry) {
        return qryExe.selectVo(qry);
    }

}