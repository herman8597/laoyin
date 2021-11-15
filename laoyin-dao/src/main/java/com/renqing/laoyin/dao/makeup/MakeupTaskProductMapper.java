package com.bat.laoyin.dao.makeup;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskProductDO;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
public interface MakeupTaskProductMapper extends BaseMapper<MakeupTaskProductDO> {

    IPage<MakeupTaskProductDO> selectPageVo(IPage<MakeupTaskProductDO> userPage, @Param("do") MakeupTaskProductDO aDO,
        @Param("sort") String sort);

    List<MakeupTaskProductDO> selectPageVo(@Param("do") MakeupTaskProductDO employeeDO, @Param("sort") String sort);

    MakeupTaskProductDO getByStatusAndProductId(@Param("status") short value, @Param("productId") Integer id);

    /**
     * 有个反射报错 不好解决 暂时使用mybatisplus 的批量保存
     * 
     * @param taskProductDOS
     */
    void insertBatch(@Param("dos") List<MakeupTaskProductDO> taskProductDOS);
}