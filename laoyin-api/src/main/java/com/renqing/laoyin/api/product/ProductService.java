package com.bat.laoyin.api.product;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.product.dto.ProductQry;
import com.bat.laoyin.dao.product.dataobject.ProductDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-30
 */
public interface ProductService extends IServiceCmd<ProductDO>, IServiceQry<ProductDO> {

    IPage<ProductDO> selectPageVo(IPage<ProductDO> page, ProductQry qry);

    List<ProductDO> selectVo(ProductQry qry);

    void mySaveList(List<ProductDO> aDos);

    void myUpdateById(ProductDO newObj);
}
