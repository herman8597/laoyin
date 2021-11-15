package com.bat.laoyin.api.sendgoods;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.sendgoods.dto.SendGoodsPlatformQry;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsPlatformDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
public interface SendGoodsPlatformService extends IServiceCmd<SendGoodsPlatformDO>, IServiceQry<SendGoodsPlatformDO> {

    IPage<SendGoodsPlatformDO> selectPageVo(IPage<SendGoodsPlatformDO> page, SendGoodsPlatformQry qry);

    List<SendGoodsPlatformDO> selectVo(SendGoodsPlatformQry qry);

}
