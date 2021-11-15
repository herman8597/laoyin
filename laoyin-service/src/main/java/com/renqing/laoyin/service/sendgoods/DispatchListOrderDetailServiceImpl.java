package com.bat.laoyin.service.sendgoods;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.sendgoods.DispatchListOrderDetailService;
import com.bat.laoyin.api.sendgoods.dto.DispatchListOrderDetailQry;
import com.bat.laoyin.dao.sendgoods.DispatchListOrderDetailMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListOrderDetailDO;
import com.bat.laoyin.service.sendgoods.executor.DispatchListOrderDetailCmdExe;
import com.bat.laoyin.service.sendgoods.executor.DispatchListOrderDetailQryExe;

/**
 * <p>
 * 发货单与订单明细关联表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-10-09
 */
@Service
public class DispatchListOrderDetailServiceImpl extends
    ServiceImpl<DispatchListOrderDetailMapper, DispatchListOrderDetailDO> implements DispatchListOrderDetailService {

    @Resource
    private DispatchListOrderDetailCmdExe cmdExe;

    @Resource
    private DispatchListOrderDetailQryExe qryExe;

    public DispatchListOrderDetailServiceImpl(@Autowired DispatchListOrderDetailCmdExe serviceCmd,
        @Autowired DispatchListOrderDetailQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<DispatchListOrderDetailDO> selectPageVo(IPage<DispatchListOrderDetailDO> page,
        DispatchListOrderDetailQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<DispatchListOrderDetailDO> selectVo(DispatchListOrderDetailQry qry) {
        return qryExe.selectVo(qry);
    }

}