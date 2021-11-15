package com.bat.laoyin.dao.sendgoods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-10-09
 */
public interface DispatchListMapper extends BaseMapper<DispatchListDO> {

    IPage<DispatchListDO> selectPageVo(IPage<DispatchListDO> userPage, @Param("do") DispatchListDO aDO,
        @Param("sort") String sort);

    IPage<DispatchListDO> selectPageVo(IPage<DispatchListDO> userPage, @Param("do") DispatchListDO aDO,
        @Param("sort") String sort, @Param("params") Map<String, Object> map);

    List<DispatchListDO> selectPageVo(@Param("do") DispatchListDO employeeDO, @Param("sort") String sort);

    DispatchListDO getByCode(String dispatchListCode);
}