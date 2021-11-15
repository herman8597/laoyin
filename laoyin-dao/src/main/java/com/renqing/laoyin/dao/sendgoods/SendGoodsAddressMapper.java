package com.bat.laoyin.dao.sendgoods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsAddressDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
public interface SendGoodsAddressMapper extends BaseMapper<SendGoodsAddressDO> {

    IPage<SendGoodsAddressDO> selectPageVo(IPage<SendGoodsAddressDO> userPage, @Param("do") SendGoodsAddressDO aDO,
        @Param("sort") String sort);

    List<SendGoodsAddressDO> selectPageVo(@Param("do") SendGoodsAddressDO employeeDO, @Param("sort") String sort);

}