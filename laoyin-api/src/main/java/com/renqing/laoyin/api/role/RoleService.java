package com.bat.laoyin.api.role;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.role.dto.RoleQry;
import com.bat.laoyin.dao.role.dataobject.RoleDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-16
 */
public interface RoleService extends IServiceCmd<RoleDO>, IServiceQry<RoleDO> {

    IPage<RoleDO> selectPageVo(IPage<RoleDO> page, RoleQry qry);

    List<RoleDO> selectVo(RoleQry qry);

}
