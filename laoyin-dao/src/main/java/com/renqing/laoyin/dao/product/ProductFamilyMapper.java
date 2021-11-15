package com.bat.laoyin.dao.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.product.dataobject.ProductFamilyDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-08-25
 */
public interface ProductFamilyMapper extends BaseMapper<ProductFamilyDO> {

    IPage<ProductFamilyDO> selectPageVo(IPage<ProductFamilyDO> userPage, @Param("do") ProductFamilyDO aDO,
        @Param("sort") String sort, @Param("ids") List<Integer> ids);

    IPage<ProductFamilyDO> selectPageVo(IPage<ProductFamilyDO> userPage, @Param("do") ProductFamilyDO aDO,
        @Param("sort") String sort, @Param("ids") List<Integer> ids, @Param("params") Map<String, Object> map);

    List<ProductFamilyDO> selectPageVo(@Param("do") ProductFamilyDO employeeDO, @Param("sort") String sort);
}