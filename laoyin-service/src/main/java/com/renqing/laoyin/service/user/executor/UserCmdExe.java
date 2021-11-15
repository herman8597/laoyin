package com.bat.laoyin.service.common.user.executor;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.user.UserMapper;
import com.bat.laoyin.dao.user.UserRoleMapper;
import com.bat.laoyin.dao.user.dataobject.UserDO;
import com.bat.laoyin.dao.user.dataobject.UserRoleDO;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class UserCmdExe extends ServiceCmdImpl<UserMapper, UserDO> {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    public void mySave(UserDO aDo) {
        try {
            userMapper.insert(aDo);
            doSave(aDo);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void myUpdateById(UserDO newObj) {
        try {
            userMapper.updateById(newObj);
            doSave(newObj);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private void doSave(UserDO aDo) {
        userRoleMapper.delete(new LambdaQueryWrapper<UserRoleDO>().eq(UserRoleDO::getUserId, aDo.getId())
            .eq(UserRoleDO::getTenantId, aDo.getTenantId()));
        aDo.getRoleIds().forEach(roleId -> {
            UserRoleDO userRoleDO = new UserRoleDO();
            userRoleDO.setRoleId(roleId);
            userRoleDO.setTenantId(aDo.getTenantId());
            userRoleDO.setUserId(aDo.getId());
            userRoleMapper.insert(userRoleDO);
        });
    }
}
