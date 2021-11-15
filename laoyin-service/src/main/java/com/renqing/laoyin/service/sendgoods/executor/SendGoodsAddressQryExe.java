package com.bat.laoyin.service.sendgoods.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.sendgoods.dto.SendGoodsAddressQry;
import com.bat.laoyin.dao.sendgoods.SendGoodsAddressMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsAddressDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.sendgoods.convertor.SendGoodsAddressConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class SendGoodsAddressQryExe extends ServiceQryImpl<SendGoodsAddressMapper, SendGoodsAddressDO> {
    @Resource
    private SendGoodsAddressMapper mapper;

    public IPage<SendGoodsAddressDO> selectPageVo(IPage<SendGoodsAddressDO> page, SendGoodsAddressQry qry) {
        SendGoodsAddressDO aDo = SendGoodsAddressConvertor.toSendGoodsAddressDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<SendGoodsAddressDO> selectVo(SendGoodsAddressQry qry) {
        SendGoodsAddressDO aDo = SendGoodsAddressConvertor.toSendGoodsAddressDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
