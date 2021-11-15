package com.bat.laoyin.api.sendgoods;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.sendgoods.dto.SendGoodsDistributionModeQry;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsDistributionModeDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
public interface SendGoodsDistributionModeService
    extends IServiceCmd<SendGoodsDistributionModeDO>, IServiceQry<SendGoodsDistributionModeDO> {

    IPage<SendGoodsDistributionModeDO> selectPageVo(IPage<SendGoodsDistributionModeDO> page,
        SendGoodsDistributionModeQry qry);

    List<SendGoodsDistributionModeDO> selectVo(SendGoodsDistributionModeQry qry);

}
