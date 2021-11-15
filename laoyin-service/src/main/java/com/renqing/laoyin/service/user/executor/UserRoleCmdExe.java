package com.bat.laoyin.service.common.user.executor;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.user.UserRoleMapper;
import com.bat.laoyin.dao.user.dataobject.UserRoleDO;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class UserRoleCmdExe extends ServiceCmdImpl<UserRoleMapper, UserRoleDO> {}
