package com.bat.laoyin.service.order.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.order.dto.OrderDetailQry;
import com.bat.laoyin.dao.order.OrderDetailMapper;
import com.bat.laoyin.dao.order.OrderInfoMapper;
import com.bat.laoyin.dao.order.dataobject.OrderDetailDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.order.convertor.OrderDetailConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class OrderDetailQryExe extends ServiceQryImpl<OrderDetailMapper, OrderDetailDO> {
    @Resource
    private OrderDetailMapper mapper;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    public IPage<OrderDetailDO> selectPageVo(IPage<OrderDetailDO> page, OrderDetailQry qry) {
        OrderDetailDO aDo = OrderDetailConvertor.toOrderDetailDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        IPage<OrderDetailDO> iPage = mapper.selectPageVo(page, aDo, sort);
        iPage.getRecords().forEach(orderDetailDO -> orderDetailDO
            .setOrderNo(orderInfoMapper.selectById(orderDetailDO.getOrderId()).getOrderNo()));
        return iPage;
    }

    public List<OrderDetailDO> selectVo(OrderDetailQry qry) {
        OrderDetailDO aDo = OrderDetailConvertor.toOrderDetailDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
