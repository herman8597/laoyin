package com.bat.laoyin.dao.makeup;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
public interface MakeupSettingMapper extends BaseMapper<MakeupSettingDO> {

    IPage<MakeupSettingDO> selectPageVo(IPage<MakeupSettingDO> userPage, @Param("do") MakeupSettingDO aDO,
        @Param("sort") String sort);

    IPage<MakeupSettingDO> selectPageVo(IPage<MakeupSettingDO> userPage, @Param("do") MakeupSettingDO aDO,
        @Param("sort") String sort, @Param("params") Map<String, Object> map);

    List<MakeupSettingDO> selectPageVo(@Param("do") MakeupSettingDO employeeDO, @Param("sort") String sort);

}