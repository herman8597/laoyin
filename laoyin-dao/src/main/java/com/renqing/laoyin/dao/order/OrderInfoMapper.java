package com.bat.laoyin.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.order.dataobject.OrderInfoDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfoDO> {

    IPage<OrderInfoDO> selectPageVo(IPage<OrderInfoDO> userPage, @Param("do") OrderInfoDO aDO,
        @Param("sort") String sort);

    IPage<OrderInfoDO> selectPageVo(IPage<OrderInfoDO> userPage, @Param("do") OrderInfoDO aDO,
        @Param("sort") String sort, @Param("params") Map<String, Object> map);

    List<OrderInfoDO> selectPageVo(@Param("do") OrderInfoDO employeeDO, @Param("sort") String sort);

}