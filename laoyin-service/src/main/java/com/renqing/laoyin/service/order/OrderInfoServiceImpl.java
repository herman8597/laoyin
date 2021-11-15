package com.bat.laoyin.service.order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.order.OrderInfoService;
import com.bat.laoyin.api.order.dto.OrderInfoQry;
import com.bat.laoyin.api.order.dto.data.OrderInfoDTO;
import com.bat.laoyin.api.third.dto.ThirdOrderCreateCmd;
import com.bat.laoyin.dao.order.OrderInfoMapper;
import com.bat.laoyin.dao.order.dataobject.OrderInfoDO;
import com.bat.laoyin.service.order.executor.OrderInfoCmdExe;
import com.bat.laoyin.service.order.executor.OrderInfoQryExe;

/**
 * <p>
 * 订单(基本信息)表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfoDO> implements OrderInfoService {

    @Resource
    private OrderInfoCmdExe cmdExe;

    @Resource
    private OrderInfoQryExe qryExe;

    public OrderInfoServiceImpl(@Autowired OrderInfoCmdExe serviceCmd, @Autowired OrderInfoQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<OrderInfoDTO> selectPageVo(IPage<OrderInfoDO> page, OrderInfoQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<OrderInfoDO> selectVo(OrderInfoQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void mySave(ThirdOrderCreateCmd cmd) {
        cmdExe.mySave(cmd);
    }

    @Override
    public List<OrderInfoDTO> myListByIds(List<Integer> ids) {
        return qryExe.myListByIds(ids);
    }

    @Override
    public void myUpdatePutById(OrderInfoDTO newObj) {
        cmdExe.myUpdatePutById(newObj);
    }

}