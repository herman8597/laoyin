package com.bat.laoyin.api.sendgoods;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.sendgoods.dto.DispatchListQry;
import com.bat.laoyin.api.sendgoods.dto.data.DispatchListDTO;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-10-09
 */
public interface DispatchListService extends IServiceCmd<DispatchListDO>, IServiceQry<DispatchListDO> {

    IPage<DispatchListDTO> selectPageVo(IPage<DispatchListDO> page, DispatchListQry qry);

    List<DispatchListDO> selectVo(DispatchListQry qry);

}
