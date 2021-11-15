package com.bat.laoyin.service.makeup.executor;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.makeup.MakeupTaskProductMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskProductDO;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class MakeupTaskProductCmdExe extends ServiceCmdImpl<MakeupTaskProductMapper, MakeupTaskProductDO> {}
