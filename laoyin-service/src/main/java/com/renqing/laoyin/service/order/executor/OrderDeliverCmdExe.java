package com.bat.laoyin.service.order.executor;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.order.OrderDeliverMapper;
import com.bat.laoyin.dao.order.dataobject.OrderDeliverDO;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class OrderDeliverCmdExe extends ServiceCmdImpl<OrderDeliverMapper, OrderDeliverDO> {}
