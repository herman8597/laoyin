package com.bat.laoyin.service.sendgoods.executor;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.sendgoods.SendGoodsAddressMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsAddressDO;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class SendGoodsAddressCmdExe extends ServiceCmdImpl<SendGoodsAddressMapper, SendGoodsAddressDO> {}
