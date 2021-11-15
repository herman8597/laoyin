package com.bat.laoyin.service.order.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.order.dto.OrderDeliverQry;
import com.bat.laoyin.dao.order.OrderDeliverMapper;
import com.bat.laoyin.dao.order.dataobject.OrderDeliverDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.order.convertor.OrderDeliverConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class OrderDeliverQryExe extends ServiceQryImpl<OrderDeliverMapper, OrderDeliverDO> {
    @Resource
    private OrderDeliverMapper mapper;

    public IPage<OrderDeliverDO> selectPageVo(IPage<OrderDeliverDO> page, OrderDeliverQry qry) {
        OrderDeliverDO aDo = OrderDeliverConvertor.toOrderDeliverDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<OrderDeliverDO> selectVo(OrderDeliverQry qry) {
        OrderDeliverDO aDo = OrderDeliverConvertor.toOrderDeliverDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
