package com.bat.laoyin.dao.makeup;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingProductDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
public interface MakeupSettingProductMapper extends BaseMapper<MakeupSettingProductDO> {

    IPage<MakeupSettingProductDO> selectPageVo(IPage<MakeupSettingProductDO> userPage,
        @Param("do") MakeupSettingProductDO aDO, @Param("sort") String sort);

    List<MakeupSettingProductDO> selectPageVo(@Param("do") MakeupSettingProductDO employeeDO,
        @Param("sort") String sort);

    List<MakeupSettingProductDO> listByProductIds(@Param("productIds") List<Integer> productIds);

    MakeupSettingProductDO getByProductId(@Param("productId") Integer id);

    List<MakeupSettingProductDO> listByMakeupSettingId(@Param("makeupSettingId") Integer id);
}