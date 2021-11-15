package com.bat.laoyin.dao.tenant;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.tenant.dataobject.TenantDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-08
 */
public interface TenantMapper extends BaseMapper<TenantDO> {

    IPage<TenantDO> selectPageVo(IPage<TenantDO> userPage, @Param("do") TenantDO aDo, @Param("sort") String sort);

    List<TenantDO> selectPageVo(@Param("do") TenantDO employeeDO, @Param("sort") String sort);

}