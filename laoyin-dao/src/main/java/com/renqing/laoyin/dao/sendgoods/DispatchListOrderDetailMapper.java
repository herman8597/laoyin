package com.bat.laoyin.dao.sendgoods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListOrderDetailDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-10-09
 */
public interface DispatchListOrderDetailMapper extends BaseMapper<DispatchListOrderDetailDO> {

    IPage<DispatchListOrderDetailDO> selectPageVo(IPage<DispatchListOrderDetailDO> userPage,
        @Param("do") DispatchListOrderDetailDO aDO, @Param("sort") String sort);

    List<DispatchListOrderDetailDO> selectPageVo(@Param("do") DispatchListOrderDetailDO employeeDO,
        @Param("sort") String sort);

    List<DispatchListOrderDetailDO> listByDispatchListId(Integer id);

    List<DispatchListOrderDetailDO> listByDispatchListIdGroupByOrderId(Integer id);

    List<DispatchListOrderDetailDO> listByOrderId(Integer orderId);

    DispatchListOrderDetailDO getByDistributionCode(String distributionCode);
}