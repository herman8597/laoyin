package com.bat.laoyin.api.product;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.product.dto.ProductSpecCmd;
import com.bat.laoyin.api.product.dto.ProductSpecQry;
import com.bat.laoyin.dao.product.dataobject.ProductSpecDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-25
 */
public interface ProductSpecService extends IServiceCmd<ProductSpecDO>, IServiceQry<ProductSpecDO> {

    IPage<ProductSpecDO> selectPageVo(IPage<ProductSpecDO> page, ProductSpecQry qry);

    List<ProductSpecDO> selectVo(ProductSpecQry qry);

    void mySaveList(ProductSpecCmd aDos);

    void myUpdateList(ProductSpecCmd aDos);
}
