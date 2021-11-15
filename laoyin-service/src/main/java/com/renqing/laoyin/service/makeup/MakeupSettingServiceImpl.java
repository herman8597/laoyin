package com.bat.laoyin.service.makeup;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.makeup.MakeupSettingService;
import com.bat.laoyin.api.makeup.dto.MakeupSettingCmd;
import com.bat.laoyin.api.makeup.dto.MakeupSettingQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupSettingRespDTO;
import com.bat.laoyin.dao.makeup.MakeupSettingMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDO;
import com.bat.laoyin.service.makeup.executor.MakeupSettingCmdExe;
import com.bat.laoyin.service.makeup.executor.MakeupSettingQryExe;

/**
 * <p>
 * 拼版设置表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Service
public class MakeupSettingServiceImpl extends ServiceImpl<MakeupSettingMapper, MakeupSettingDO>
    implements MakeupSettingService {

    @Resource
    private MakeupSettingCmdExe cmdExe;

    @Resource
    private MakeupSettingQryExe qryExe;

    public MakeupSettingServiceImpl(@Autowired MakeupSettingCmdExe serviceCmd,
        @Autowired MakeupSettingQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<MakeupSettingDO> selectPageVo(IPage<MakeupSettingDO> page, MakeupSettingQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<MakeupSettingDO> selectVo(MakeupSettingQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void mySave(MakeupSettingCmd aDo) {
        cmdExe.mySave(aDo);
    }

    @Override
    public void myUpdateById(MakeupSettingCmd newObj) {
        cmdExe.myUpdateById(newObj);
    }

    @Override
    public List<MakeupSettingRespDTO> myListByIds(List<Integer> ids) {
        return qryExe.myListByIds(ids);
    }

}