package com.bat.laoyin.service.sendgoods;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.sendgoods.SendGoodsPlatformService;
import com.bat.laoyin.api.sendgoods.dto.SendGoodsPlatformQry;
import com.bat.laoyin.dao.sendgoods.SendGoodsPlatformMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsPlatformDO;
import com.bat.laoyin.service.sendgoods.executor.SendGoodsPlatformCmdExe;
import com.bat.laoyin.service.sendgoods.executor.SendGoodsPlatformQryExe;

/**
 * <p>
 * 发货平台表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
@Service
public class SendGoodsPlatformServiceImpl extends ServiceImpl<SendGoodsPlatformMapper, SendGoodsPlatformDO>
    implements SendGoodsPlatformService {

    @Resource
    private SendGoodsPlatformCmdExe cmdExe;

    @Resource
    private SendGoodsPlatformQryExe qryExe;

    public SendGoodsPlatformServiceImpl(@Autowired SendGoodsPlatformCmdExe serviceCmd,
        @Autowired SendGoodsPlatformQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<SendGoodsPlatformDO> selectPageVo(IPage<SendGoodsPlatformDO> page, SendGoodsPlatformQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<SendGoodsPlatformDO> selectVo(SendGoodsPlatformQry qry) {
        return qryExe.selectVo(qry);
    }

}