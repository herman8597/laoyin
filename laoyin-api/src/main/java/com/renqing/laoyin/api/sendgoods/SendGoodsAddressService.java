package com.bat.laoyin.api.sendgoods;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.sendgoods.dto.SendGoodsAddressQry;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsAddressDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
public interface SendGoodsAddressService extends IServiceCmd<SendGoodsAddressDO>, IServiceQry<SendGoodsAddressDO> {

    IPage<SendGoodsAddressDO> selectPageVo(IPage<SendGoodsAddressDO> page, SendGoodsAddressQry qry);

    List<SendGoodsAddressDO> selectVo(SendGoodsAddressQry qry);

}
