package com.bat.laoyin.dao.makeup;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDeadlineDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
public interface MakeupSettingDeadlineMapper extends BaseMapper<MakeupSettingDeadlineDO> {

    IPage<MakeupSettingDeadlineDO> selectPageVo(IPage<MakeupSettingDeadlineDO> userPage,
        @Param("do") MakeupSettingDeadlineDO aDO, @Param("sort") String sort);

    List<MakeupSettingDeadlineDO> selectPageVo(@Param("do") MakeupSettingDeadlineDO employeeDO,
        @Param("sort") String sort);

    MakeupSettingDeadlineDO getNextDeadlineTime(@Param("makeupSettingId") Integer makeupSettingId,
        @Param("date") Date date);

    List<MakeupSettingDeadlineDO> listByMakeupSettingId(Integer id);
}