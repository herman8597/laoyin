package com.bat.laoyin.service.common.region.executor;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.region.RegionMapper;
import com.bat.laoyin.dao.region.dataobject.RegionDO;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class RegionCmdExe extends ServiceCmdImpl<RegionMapper, RegionDO> {}
