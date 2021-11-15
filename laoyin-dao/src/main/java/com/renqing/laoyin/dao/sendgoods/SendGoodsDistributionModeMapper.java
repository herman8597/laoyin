package com.bat.laoyin.dao.sendgoods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsDistributionModeDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
public interface SendGoodsDistributionModeMapper extends BaseMapper<SendGoodsDistributionModeDO> {

    IPage<SendGoodsDistributionModeDO> selectPageVo(IPage<SendGoodsDistributionModeDO> userPage,
        @Param("do") SendGoodsDistributionModeDO aDO, @Param("sort") String sort);

    List<SendGoodsDistributionModeDO> selectPageVo(@Param("do") SendGoodsDistributionModeDO employeeDO,
        @Param("sort") String sort);

}