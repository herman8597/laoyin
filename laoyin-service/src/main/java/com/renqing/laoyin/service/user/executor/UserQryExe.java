package com.bat.laoyin.service.common.user.executor;

import static com.bat.laoyin.service.common.constants.Constant.UNKNOWN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.user.dto.UserQry;
import com.bat.laoyin.dao.role.RoleMapper;
import com.bat.laoyin.dao.role.dataobject.RoleDO;
import com.bat.laoyin.dao.user.UserMapper;
import com.bat.laoyin.dao.user.UserRoleMapper;
import com.bat.laoyin.dao.user.dataobject.UserDO;
import com.bat.laoyin.dao.user.dataobject.UserRoleDO;
import com.bat.laoyin.service.common.user.convertor.UserConvertor;
import com.bat.laoyin.service.common.utils.SqlUtils;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class UserQryExe extends ServiceQryImpl<UserMapper, UserDO> {
    @Resource
    private UserMapper mapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    public IPage<UserDO> selectPageVo(IPage<UserDO> page, UserQry qry) {
        UserDO aDo = UserConvertor.toUserDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        IPage<UserDO> userDOIPage = mapper.selectPageVo(page, aDo, sort);
        userDOIPage.getRecords().stream()
            .peek(userDO -> userDO.setRoleIds(getUserRole(userDO.getId(), userDO.getTenantId()).stream()
                .map(UserRoleDO::getRoleId).collect(Collectors.toList())))
            .collect(Collectors.toList());
        return userDOIPage;
    }

    public List<UserDO> selectVo(UserQry qry) {
        UserDO aDo = UserConvertor.toUserDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }

    public List<RoleDO> getRoleInfo(Integer id, Integer tenantId) {
        List<UserRoleDO> userRoleDOS = getUserRole(id, tenantId);
        if (!CollectionUtils.isEmpty(userRoleDOS)) {
            return userRoleDOS.stream().map(userRoleDO -> roleMapper.selectById(userRoleDO.getRoleId()))
                .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private List<UserRoleDO> getUserRole(Integer id, Integer tenantId) {
        List<UserRoleDO> userRoleDOS = userRoleMapper.selectList(new QueryWrapper<UserRoleDO>().lambda()
            .eq(UserRoleDO::getUserId, id).eq(UserRoleDO::getTenantId, tenantId));
        return userRoleDOS;
    }

    public List<UserDO> myListByIds(List<Integer> ids) {
        List<UserDO> userDOS = mapper.selectBatchIds(ids);
        return userDOS.stream().peek(userDO -> userDO.setRoleIds(getUserRole(userDO.getId(), userDO.getTenantId())
            .stream().map(UserRoleDO::getRoleId).collect(Collectors.toList()))).collect(Collectors.toList());
    }

    public String getUserNameById(Integer userId) {
        UserDO userDO = mapper.selectById(userId);
        return userDO != null ? userDO.getRealName() : UNKNOWN;
    }
}
