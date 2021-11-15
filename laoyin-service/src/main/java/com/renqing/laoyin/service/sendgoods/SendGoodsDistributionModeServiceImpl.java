package com.bat.laoyin.service.sendgoods;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.sendgoods.SendGoodsDistributionModeService;
import com.bat.laoyin.api.sendgoods.dto.SendGoodsDistributionModeQry;
import com.bat.laoyin.dao.sendgoods.SendGoodsDistributionModeMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsDistributionModeDO;
import com.bat.laoyin.service.sendgoods.executor.SendGoodsDistributionModeCmdExe;
import com.bat.laoyin.service.sendgoods.executor.SendGoodsDistributionModeQryExe;

/**
 * <p>
 * 发货配送方式 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
@Service
public class SendGoodsDistributionModeServiceImpl
    extends ServiceImpl<SendGoodsDistributionModeMapper, SendGoodsDistributionModeDO>
    implements SendGoodsDistributionModeService {

    @Resource
    private SendGoodsDistributionModeCmdExe cmdExe;

    @Resource
    private SendGoodsDistributionModeQryExe qryExe;

    public SendGoodsDistributionModeServiceImpl(@Autowired SendGoodsDistributionModeCmdExe serviceCmd,
        @Autowired SendGoodsDistributionModeQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<SendGoodsDistributionModeDO> selectPageVo(IPage<SendGoodsDistributionModeDO> page,
        SendGoodsDistributionModeQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<SendGoodsDistributionModeDO> selectVo(SendGoodsDistributionModeQry qry) {
        return qryExe.selectVo(qry);
    }

}