package com.bat.laoyin.service.order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.order.OrderDetailService;
import com.bat.laoyin.api.order.dto.OrderDetailQry;
import com.bat.laoyin.dao.order.OrderDetailMapper;
import com.bat.laoyin.dao.order.dataobject.OrderDetailDO;
import com.bat.laoyin.service.order.executor.OrderDetailCmdExe;
import com.bat.laoyin.service.order.executor.OrderDetailQryExe;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetailDO>
    implements OrderDetailService {

    @Resource
    private OrderDetailCmdExe cmdExe;

    @Resource
    private OrderDetailQryExe qryExe;

    public OrderDetailServiceImpl(@Autowired OrderDetailCmdExe serviceCmd, @Autowired OrderDetailQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<OrderDetailDO> selectPageVo(IPage<OrderDetailDO> page, OrderDetailQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<OrderDetailDO> selectVo(OrderDetailQry qry) {
        return qryExe.selectVo(qry);
    }

}