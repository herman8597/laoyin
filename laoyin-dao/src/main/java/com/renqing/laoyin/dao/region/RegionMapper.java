package com.bat.laoyin.dao.region;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.region.dataobject.RegionDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-17
 */
public interface RegionMapper extends BaseMapper<RegionDO> {

    IPage<RegionDO> selectPageVo(IPage<RegionDO> userPage, @Param("do") RegionDO regionDO, @Param("sort") String sort);

    List<RegionDO> selectPageVo(@Param("do") RegionDO employeeDO, @Param("sort") String sort);

}