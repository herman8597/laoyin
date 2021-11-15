package com.bat.laoyin.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.product.dataobject.ProductCategoryDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategoryDO> {

    IPage<ProductCategoryDO> selectPageVo(IPage<ProductCategoryDO> userPage, @Param("do") ProductCategoryDO aDO,
        @Param("sort") String sort);

    List<ProductCategoryDO> selectPageVo(@Param("do") ProductCategoryDO employeeDO, @Param("sort") String sort);

}