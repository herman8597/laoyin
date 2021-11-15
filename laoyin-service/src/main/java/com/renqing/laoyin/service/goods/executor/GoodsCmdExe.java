package com.bat.laoyin.service.goods.executor;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.goods.GoodsMapper;
import com.bat.laoyin.dao.goods.dataobject.GoodsDO;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class GoodsCmdExe extends ServiceCmdImpl<GoodsMapper, GoodsDO> {}
