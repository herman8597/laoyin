package com.bat.laoyin.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.user.dataobject.UserDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-07
 */
public interface UserMapper extends BaseMapper<UserDO> {

    IPage<UserDO> selectPageVo(IPage<UserDO> userPage, @Param("do") UserDO aDo, @Param("sort") String sort);

    List<UserDO> selectPageVo(@Param("do") UserDO employeeDO, @Param("sort") String sort);

}