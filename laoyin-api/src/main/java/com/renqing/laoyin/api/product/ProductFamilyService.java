package com.bat.laoyin.api.product;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.product.dto.ProductFamilyQry;
import com.bat.laoyin.api.product.dto.data.ProductFamilyDTO;
import com.bat.laoyin.dao.product.dataobject.ProductFamilyDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-25
 */
public interface ProductFamilyService extends IServiceCmd<ProductFamilyDO>, IServiceQry<ProductFamilyDO> {

    IPage<ProductFamilyDTO> selectPageVo(IPage<ProductFamilyDO> page, ProductFamilyQry qry);

    List<ProductFamilyDO> selectVo(ProductFamilyQry qry);

    void myUpdateById(ProductFamilyDO newObj);
}
