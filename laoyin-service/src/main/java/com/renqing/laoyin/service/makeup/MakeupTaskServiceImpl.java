package com.bat.laoyin.service.makeup;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.makeup.MakeupTaskService;
import com.bat.laoyin.api.makeup.dto.MakeupTaskQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupTaskDTO;
import com.bat.laoyin.dao.makeup.MakeupTaskMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskDO;
import com.bat.laoyin.service.makeup.executor.MakeupTaskCmdExe;
import com.bat.laoyin.service.makeup.executor.MakeupTaskQryExe;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 拼版任务表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Service
@Slf4j
public class MakeupTaskServiceImpl extends ServiceImpl<MakeupTaskMapper, MakeupTaskDO>
    implements MakeupTaskService {

    @Resource
    private MakeupTaskCmdExe cmdExe;

    @Resource
    private MakeupTaskQryExe qryExe;

    public MakeupTaskServiceImpl(@Autowired MakeupTaskCmdExe serviceCmd, @Autowired MakeupTaskQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<MakeupTaskDTO> selectPageVo(IPage<MakeupTaskDO> page, MakeupTaskQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<MakeupTaskDO> selectVo(MakeupTaskQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void makeupOrder(Integer orderId) {
        cmdExe.makeupOrder(orderId);
    }

    @Override
    public void doDeadLine(Integer makeupTaskId) {
        cmdExe.doDeadLine(makeupTaskId);
    }

}