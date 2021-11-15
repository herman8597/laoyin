package com.bat.laoyin.service.common.user.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.user.dto.UserRoleQry;
import com.bat.laoyin.dao.user.UserRoleMapper;
import com.bat.laoyin.dao.user.dataobject.UserRoleDO;
import com.bat.laoyin.service.common.user.convertor.UserRoleConvertor;
import com.bat.laoyin.service.common.utils.SqlUtils;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class UserRoleQryExe extends ServiceQryImpl<UserRoleMapper, UserRoleDO> {
    @Resource
    private UserRoleMapper mapper;

    public IPage<UserRoleDO> selectPageVo(IPage<UserRoleDO> page, UserRoleQry qry) {
        UserRoleDO aDo = UserRoleConvertor.toUserRoleDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<UserRoleDO> selectVo(UserRoleQry qry) {
        UserRoleDO aDo = UserRoleConvertor.toUserRoleDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
