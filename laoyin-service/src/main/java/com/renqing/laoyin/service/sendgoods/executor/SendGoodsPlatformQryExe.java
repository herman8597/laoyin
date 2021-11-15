package com.bat.laoyin.service.sendgoods.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.sendgoods.dto.SendGoodsPlatformQry;
import com.bat.laoyin.dao.sendgoods.SendGoodsPlatformMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsPlatformDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.sendgoods.convertor.SendGoodsPlatformConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class SendGoodsPlatformQryExe extends ServiceQryImpl<SendGoodsPlatformMapper, SendGoodsPlatformDO> {
    @Resource
    private SendGoodsPlatformMapper mapper;

    public IPage<SendGoodsPlatformDO> selectPageVo(IPage<SendGoodsPlatformDO> page, SendGoodsPlatformQry qry) {
        SendGoodsPlatformDO aDo = SendGoodsPlatformConvertor.toSendGoodsPlatformDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<SendGoodsPlatformDO> selectVo(SendGoodsPlatformQry qry) {
        SendGoodsPlatformDO aDo = SendGoodsPlatformConvertor.toSendGoodsPlatformDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
