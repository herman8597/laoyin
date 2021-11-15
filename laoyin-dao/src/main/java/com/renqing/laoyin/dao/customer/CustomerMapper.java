package com.bat.laoyin.dao.customer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.customer.dataobject.CustomerDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-09-03
 */
public interface CustomerMapper extends BaseMapper<CustomerDO> {

    IPage<CustomerDO> selectPageVo(IPage<CustomerDO> userPage, @Param("do") CustomerDO aDO, @Param("sort") String sort);

    List<CustomerDO> selectPageVo(@Param("do") CustomerDO employeeDO, @Param("sort") String sort);

}