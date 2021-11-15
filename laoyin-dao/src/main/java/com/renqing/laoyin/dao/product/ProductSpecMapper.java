package com.bat.laoyin.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.product.dataobject.ProductSpecDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-25
 */
public interface ProductSpecMapper extends BaseMapper<ProductSpecDO> {

    IPage<ProductSpecDO> selectPageVo(IPage<ProductSpecDO> userPage, @Param("do") ProductSpecDO aDO,
        @Param("sort") String sort);

    List<ProductSpecDO> selectPageVo(@Param("do") ProductSpecDO employeeDO, @Param("sort") String sort);

}