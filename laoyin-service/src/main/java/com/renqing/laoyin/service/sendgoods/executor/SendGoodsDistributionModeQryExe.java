package com.bat.laoyin.service.sendgoods.executor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.sendgoods.dto.SendGoodsDistributionModeQry;
import com.bat.laoyin.dao.sendgoods.SendGoodsDistributionModeMapper;
import com.bat.laoyin.dao.sendgoods.SendGoodsPlatformMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsDistributionModeDO;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsPlatformDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.sendgoods.convertor.SendGoodsDistributionModeConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class SendGoodsDistributionModeQryExe
    extends ServiceQryImpl<SendGoodsDistributionModeMapper, SendGoodsDistributionModeDO> {
    @Resource
    private SendGoodsDistributionModeMapper mapper;

    @Resource
    private SendGoodsPlatformMapper platformMapper;

    public IPage<SendGoodsDistributionModeDO> selectPageVo(IPage<SendGoodsDistributionModeDO> page,
        SendGoodsDistributionModeQry qry) {
        SendGoodsDistributionModeDO aDo = SendGoodsDistributionModeConvertor.toSendGoodsDistributionModeDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        IPage<SendGoodsDistributionModeDO> pageVo = mapper.selectPageVo(page, aDo, sort);
        pageVo.getRecords().forEach(sendGoodsDistributionModeDO -> {
            List<String> platformCodes = Arrays.asList(sendGoodsDistributionModeDO.getPlatformCode().split(","));
            String platformNames = platformMapper.listByCode(platformCodes).stream().map(SendGoodsPlatformDO::getName)
                .collect(Collectors.joining(","));
            sendGoodsDistributionModeDO.setPlatformName(platformNames);
        });
        return pageVo;
    }

    public List<SendGoodsDistributionModeDO> selectVo(SendGoodsDistributionModeQry qry) {
        SendGoodsDistributionModeDO aDo = SendGoodsDistributionModeConvertor.toSendGoodsDistributionModeDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
