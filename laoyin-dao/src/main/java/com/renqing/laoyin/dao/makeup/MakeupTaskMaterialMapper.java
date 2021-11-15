package com.bat.laoyin.dao.makeup;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskMaterialDO;
import com.bat.laoyin.dao.makeup.dataobject.MyPickListItemDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
public interface MakeupTaskMaterialMapper extends BaseMapper<MakeupTaskMaterialDO> {

    IPage<MakeupTaskMaterialDO> selectPageVo(IPage<MakeupTaskMaterialDO> userPage,
        @Param("do") MakeupTaskMaterialDO aDO, @Param("sort") String sort);

    List<MakeupTaskMaterialDO> selectPageVo(@Param("do") MakeupTaskMaterialDO employeeDO, @Param("sort") String sort);

    List<MakeupTaskMaterialDO> listByMakeupTaskId(@Param("makeupTaskId") Integer id);

    List<MyPickListItemDO> getMaterialRequisition(Integer makeupTaskId);

    List<MakeupTaskMaterialDO> listByMakeupTaskIdGroupByOrderDetailId(@Param("makeupTaskId") Integer makeupTaskId);

    List<Integer> listOrderIdByMakeupTaskIdGroupByOrderId(@Param("makeupTaskId") Integer id);

    List<Integer> listOrderDetailIdByMakeupTaskIdGroupByOrderDetailId(@Param("makeupTaskId") Integer makeupTaskId);

    Integer getLastIndexByOrderId(@Param("orderId") Integer orderId);

    List<MakeupTaskMaterialDO> listByOrderDetailIds(@Param("orderDetailIds") List<Integer> orderDetailIds);

    List<MakeupTaskMaterialDO>
        listByOrderDetailIdGroupByDistributionCode(@Param("orderDetailId") Integer orderDetailId);

    List<MakeupTaskMaterialDO> listByOrderId(@Param("orderId") Integer orderId);

    List<MakeupTaskMaterialDO> listByDistributionCode(String distributionCode);

    MakeupTaskMaterialDO getByDistributionCodeAndDistributionCodeIndex(
        @Param("distributionCode") String distributionCode, @Param("distributionCodeIndex") int distributionCodeIndex);
}