package com.bat.laoyin.service.sendgoods.executor;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.sendgoods.DispatchListOrderDetailMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListOrderDetailDO;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class DispatchListOrderDetailCmdExe
    extends ServiceCmdImpl<DispatchListOrderDetailMapper, DispatchListOrderDetailDO> {}
