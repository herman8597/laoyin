package com.bat.laoyin.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.product.dataobject.ProductBrandDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
public interface ProductBrandMapper extends BaseMapper<ProductBrandDO> {

    IPage<ProductBrandDO> selectPageVo(IPage<ProductBrandDO> userPage, @Param("do") ProductBrandDO aDO,
        @Param("sort") String sort);

    List<ProductBrandDO> selectPageVo(@Param("do") ProductBrandDO employeeDO, @Param("sort") String sort);

}