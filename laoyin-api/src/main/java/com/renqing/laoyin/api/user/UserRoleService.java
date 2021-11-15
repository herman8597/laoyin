package com.bat.laoyin.api.user;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.user.dto.UserRoleQry;
import com.bat.laoyin.dao.user.dataobject.UserRoleDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-16
 */
public interface UserRoleService extends IServiceCmd<UserRoleDO>, IServiceQry<UserRoleDO> {

    IPage<UserRoleDO> selectPageVo(IPage<UserRoleDO> page, UserRoleQry qry);

    List<UserRoleDO> selectVo(UserRoleQry qry);

}
