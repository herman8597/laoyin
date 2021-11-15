package com.bat.laoyin.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.role.dataobject.RoleDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-16
 */
public interface RoleMapper extends BaseMapper<RoleDO> {

    IPage<RoleDO> selectPageVo(IPage<RoleDO> userPage, @Param("do") RoleDO aDo, @Param("sort") String sort);

    List<RoleDO> selectPageVo(@Param("do") RoleDO employeeDO, @Param("sort") String sort);

}