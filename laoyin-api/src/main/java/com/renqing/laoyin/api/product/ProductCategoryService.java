package com.bat.laoyin.api.product;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.product.dto.ProductCategoryQry;
import com.bat.laoyin.dao.product.dataobject.ProductCategoryDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
public interface ProductCategoryService extends IServiceCmd<ProductCategoryDO>, IServiceQry<ProductCategoryDO> {

    IPage<ProductCategoryDO> selectPageVo(IPage<ProductCategoryDO> page, ProductCategoryQry qry);

    List<ProductCategoryDO> selectVo(ProductCategoryQry qry);

    void myUpdateById(ProductCategoryDO newObj);
}
