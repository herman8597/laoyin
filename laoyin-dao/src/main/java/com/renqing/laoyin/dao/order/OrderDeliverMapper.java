package com.bat.laoyin.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.order.dataobject.OrderDeliverDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
public interface OrderDeliverMapper extends BaseMapper<OrderDeliverDO> {

    IPage<OrderDeliverDO> selectPageVo(IPage<OrderDeliverDO> userPage, @Param("do") OrderDeliverDO aDO,
        @Param("sort") String sort);

    List<OrderDeliverDO> selectPageVo(@Param("do") OrderDeliverDO employeeDO, @Param("sort") String sort);

    OrderDeliverDO getByOrderId(@Param("orderId") Integer id);
}