package com.bat.laoyin.service.tenant.executor;


import static com.bat.laoyin.service.common.constants.Constant.DEL_NO;
import static com.bat.laoyin.service.common.constants.Constant.OPEN_YES;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.tenant.TenantMapper;
import com.bat.laoyin.dao.tenant.dataobject.TenantDO;
import com.bat.laoyin.dao.user.UserMapper;
import com.bat.laoyin.dao.user.dataobject.UserDO;

/**
 * @author: lim
 * @description:
 * @date: 2021/8/4 23:29
 */
@Component
public class TenantCmdExe extends ServiceCmdImpl<TenantMapper, TenantDO> {

    @Resource
    private TenantMapper mapper;

    @Resource
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public void mySave(TenantDO aDo) {
        try {
            mapper.insert(aDo);
            UserDO userDO = new UserDO();
            userDO.setUserName(aDo.getContactName());
            userDO.setRealName(aDo.getContactName());
            userDO.setDeleteFlag(DEL_NO);
            userDO.setPassword(aDo.getContactPassword());
            userDO.setPhone(aDo.getContactPhone());
            userDO.setStatus(OPEN_YES);
            userDO.setTenantId(aDo.getId());
            // TODO 添加角色
            userMapper.insert(userDO);
            TenantDO tenantDO = aDo.setTenantId(aDo.getId());
            tenantDO.setContactId(userDO.getId());
            mapper.updateById(tenantDO);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
