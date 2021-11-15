package com.bat.laoyin.dao.goods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.goods.dataobject.GoodsDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
public interface GoodsMapper extends BaseMapper<GoodsDO> {

    IPage<GoodsDO> selectPageVo(IPage<GoodsDO> userPage, @Param("do") GoodsDO aDO, @Param("sort") String sort);

    List<GoodsDO> selectPageVo(@Param("do") GoodsDO employeeDO, @Param("sort") String sort);

    IPage<GoodsDO> selectPageVo(IPage<GoodsDO> userPage, @Param("do") GoodsDO aDO, @Param("sort") String sort,
        @Param("params") Map<String, Object> map);

}