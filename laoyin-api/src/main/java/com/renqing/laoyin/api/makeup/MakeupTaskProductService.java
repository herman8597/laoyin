package com.bat.laoyin.api.makeup;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.makeup.dto.MakeupTaskProductQry;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskProductDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
public interface MakeupTaskProductService extends IServiceCmd<MakeupTaskProductDO>, IServiceQry<MakeupTaskProductDO> {

    IPage<MakeupTaskProductDO> selectPageVo(IPage<MakeupTaskProductDO> page, MakeupTaskProductQry qry);

    List<MakeupTaskProductDO> selectVo(MakeupTaskProductQry qry);

}
