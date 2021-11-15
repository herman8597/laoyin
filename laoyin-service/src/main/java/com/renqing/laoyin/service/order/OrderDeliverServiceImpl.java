package com.bat.laoyin.service.order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.order.OrderDeliverService;
import com.bat.laoyin.api.order.dto.OrderDeliverQry;
import com.bat.laoyin.dao.order.OrderDeliverMapper;
import com.bat.laoyin.dao.order.dataobject.OrderDeliverDO;
import com.bat.laoyin.service.order.executor.OrderDeliverCmdExe;
import com.bat.laoyin.service.order.executor.OrderDeliverQryExe;

/**
 * <p>
 * 订单发货信息表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Service
public class OrderDeliverServiceImpl extends ServiceImpl<OrderDeliverMapper, OrderDeliverDO>
    implements OrderDeliverService {

    @Resource
    private OrderDeliverCmdExe cmdExe;

    @Resource
    private OrderDeliverQryExe qryExe;

    public OrderDeliverServiceImpl(@Autowired OrderDeliverCmdExe serviceCmd, @Autowired OrderDeliverQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<OrderDeliverDO> selectPageVo(IPage<OrderDeliverDO> page, OrderDeliverQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<OrderDeliverDO> selectVo(OrderDeliverQry qry) {
        return qryExe.selectVo(qry);
    }

}