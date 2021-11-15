package com.bat.laoyin.service.sendgoods;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.sendgoods.SendGoodsAddressService;
import com.bat.laoyin.api.sendgoods.dto.SendGoodsAddressQry;
import com.bat.laoyin.dao.sendgoods.SendGoodsAddressMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsAddressDO;
import com.bat.laoyin.service.sendgoods.executor.SendGoodsAddressCmdExe;
import com.bat.laoyin.service.sendgoods.executor.SendGoodsAddressQryExe;

/**
 * <p>
 * 发货地址表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
@Service
public class SendGoodsAddressServiceImpl extends ServiceImpl<SendGoodsAddressMapper, SendGoodsAddressDO>
    implements SendGoodsAddressService {

    @Resource
    private SendGoodsAddressCmdExe cmdExe;

    @Resource
    private SendGoodsAddressQryExe qryExe;

    public SendGoodsAddressServiceImpl(@Autowired SendGoodsAddressCmdExe serviceCmd,
        @Autowired SendGoodsAddressQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<SendGoodsAddressDO> selectPageVo(IPage<SendGoodsAddressDO> page, SendGoodsAddressQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<SendGoodsAddressDO> selectVo(SendGoodsAddressQry qry) {
        return qryExe.selectVo(qry);
    }

}