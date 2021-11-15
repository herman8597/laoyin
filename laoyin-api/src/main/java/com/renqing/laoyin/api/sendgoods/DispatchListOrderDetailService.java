package com.bat.laoyin.api.sendgoods;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.sendgoods.dto.DispatchListOrderDetailQry;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListOrderDetailDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-10-09
 */
public interface DispatchListOrderDetailService
    extends IServiceCmd<DispatchListOrderDetailDO>, IServiceQry<DispatchListOrderDetailDO> {

    IPage<DispatchListOrderDetailDO> selectPageVo(IPage<DispatchListOrderDetailDO> page,
        DispatchListOrderDetailQry qry);

    List<DispatchListOrderDetailDO> selectVo(DispatchListOrderDetailQry qry);

}
