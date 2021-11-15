package com.bat.laoyin.api.product;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.product.dto.ProductBrandQry;
import com.bat.laoyin.dao.product.dataobject.ProductBrandDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
public interface ProductBrandService extends IServiceCmd<ProductBrandDO>, IServiceQry<ProductBrandDO> {

    IPage<ProductBrandDO> selectPageVo(IPage<ProductBrandDO> page, ProductBrandQry qry);

    List<ProductBrandDO> selectVo(ProductBrandQry qry);

    void myUpdateById(ProductBrandDO newObj);
}
