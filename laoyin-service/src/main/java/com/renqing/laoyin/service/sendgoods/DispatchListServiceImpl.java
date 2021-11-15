package com.bat.laoyin.service.sendgoods;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.sendgoods.DispatchListService;
import com.bat.laoyin.api.sendgoods.dto.DispatchListQry;
import com.bat.laoyin.api.sendgoods.dto.data.DispatchListDTO;
import com.bat.laoyin.dao.sendgoods.DispatchListMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListDO;
import com.bat.laoyin.service.sendgoods.executor.DispatchListCmdExe;
import com.bat.laoyin.service.sendgoods.executor.DispatchListQryExe;

/**
 * <p>
 * 发货单表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-10-09
 */
@Service
public class DispatchListServiceImpl extends ServiceImpl<DispatchListMapper, DispatchListDO>
    implements DispatchListService {

    @Resource
    private DispatchListCmdExe cmdExe;

    @Resource
    private DispatchListQryExe qryExe;

    public DispatchListServiceImpl(@Autowired DispatchListCmdExe serviceCmd, @Autowired DispatchListQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<DispatchListDTO> selectPageVo(IPage<DispatchListDO> page, DispatchListQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<DispatchListDO> selectVo(DispatchListQry qry) {
        return qryExe.selectVo(qry);
    }

}