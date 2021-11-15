package com.bat.laoyin.service.makeup;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.makeup.MakeupSettingDeadlineService;
import com.bat.laoyin.api.makeup.dto.MakeupSettingDeadlineQry;
import com.bat.laoyin.dao.makeup.MakeupSettingDeadlineMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDeadlineDO;
import com.bat.laoyin.service.makeup.executor.MakeupSettingDeadlineCmdExe;
import com.bat.laoyin.service.makeup.executor.MakeupSettingDeadlineQryExe;

/**
 * <p>
 * 拼版设置截止时间关联表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Service
public class MakeupSettingDeadlineServiceImpl extends ServiceImpl<MakeupSettingDeadlineMapper, MakeupSettingDeadlineDO>
    implements MakeupSettingDeadlineService {

    @Resource
    private MakeupSettingDeadlineCmdExe cmdExe;

    @Resource
    private MakeupSettingDeadlineQryExe qryExe;

    public MakeupSettingDeadlineServiceImpl(@Autowired MakeupSettingDeadlineCmdExe serviceCmd,
        @Autowired MakeupSettingDeadlineQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<MakeupSettingDeadlineDO> selectPageVo(IPage<MakeupSettingDeadlineDO> page,
        MakeupSettingDeadlineQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<MakeupSettingDeadlineDO> selectVo(MakeupSettingDeadlineQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void fixDeadlineTime() {
        cmdExe.fixDeadlineTime();
    }

}