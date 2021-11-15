package com.bat.laoyin.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.product.dataobject.ProductDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-30
 */
public interface ProductMapper extends BaseMapper<ProductDO> {

    IPage<ProductDO> selectPageVo(IPage<ProductDO> userPage, @Param("do") ProductDO aDO, @Param("sort") String sort);

    List<ProductDO> selectPageVo(@Param("do") ProductDO employeeDO, @Param("sort") String sort);

    ProductDO getByCode(@Param("productCode") String productCode);

    void updateBrandNameByBrandId(@Param("brandId") Integer id, @Param("brandName") String name);

    void updateCategoryByCategoryId(@Param("categoryId") Integer id, @Param("categoryName") String name);

    void updateFamilyNameByFamilyId(@Param("familyId") Integer id, @Param("familyName") String name);

    void updateSpecNameBySpecId(@Param("specId") Integer id, @Param("specName") String name);
}