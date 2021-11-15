package com.bat.laoyin.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.user.dataobject.UserRoleDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-16
 */
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

    IPage<UserRoleDO> selectPageVo(IPage<UserRoleDO> userPage, @Param("do") UserRoleDO aDo, @Param("sort") String sort);

    List<UserRoleDO> selectPageVo(@Param("do") UserRoleDO employeeDO, @Param("sort") String sort);

}