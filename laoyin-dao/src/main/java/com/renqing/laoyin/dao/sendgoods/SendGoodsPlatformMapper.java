package com.bat.laoyin.dao.sendgoods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsPlatformDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
public interface SendGoodsPlatformMapper extends BaseMapper<SendGoodsPlatformDO> {

    IPage<SendGoodsPlatformDO> selectPageVo(IPage<SendGoodsPlatformDO> userPage, @Param("do") SendGoodsPlatformDO aDO,
        @Param("sort") String sort);

    List<SendGoodsPlatformDO> selectPageVo(@Param("do") SendGoodsPlatformDO employeeDO, @Param("sort") String sort);

    List<SendGoodsPlatformDO> listByCode(@Param("codes") List<String> codes);

}