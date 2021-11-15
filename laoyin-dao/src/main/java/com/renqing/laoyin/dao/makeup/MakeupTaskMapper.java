package com.bat.laoyin.dao.makeup;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
public interface MakeupTaskMapper extends BaseMapper<MakeupTaskDO> {

    IPage<MakeupTaskDO> selectPageVo(IPage<MakeupTaskDO> userPage, @Param("do") MakeupTaskDO aDO,
        @Param("sort") String sort);

    IPage<MakeupTaskDO> selectPageVo(IPage<MakeupTaskDO> userPage, @Param("do") MakeupTaskDO aDO,
        @Param("sort") String sort, @Param("params") Map<String, Object> map);

    List<MakeupTaskDO> selectPageVo(@Param("do") MakeupTaskDO employeeDO, @Param("sort") String sort);

    MakeupTaskDO getByCode(String makeupTaskCode);
}