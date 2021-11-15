package com.bat.laoyin.service.common.role.executor;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.role.RoleMapper;
import com.bat.laoyin.dao.role.dataobject.RoleDO;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class RoleCmdExe extends ServiceCmdImpl<RoleMapper, RoleDO> {}
