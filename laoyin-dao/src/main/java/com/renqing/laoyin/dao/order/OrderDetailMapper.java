package com.bat.laoyin.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.order.dataobject.OrderDetailDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetailDO> {

    IPage<OrderDetailDO> selectPageVo(IPage<OrderDetailDO> userPage, @Param("do") OrderDetailDO aDO,
        @Param("sort") String sort);

    List<OrderDetailDO> selectPageVo(@Param("do") OrderDetailDO employeeDO, @Param("sort") String sort);

    List<OrderDetailDO> listByOrderId(@Param("orderId") Integer id);

}