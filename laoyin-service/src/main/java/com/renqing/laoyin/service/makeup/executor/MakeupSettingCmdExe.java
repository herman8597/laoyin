package com.bat.laoyin.service.makeup.executor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.api.makeup.dto.MakeupSettingCmd;
import com.bat.laoyin.dao.makeup.MakeupSettingDeadlineMapper;
import com.bat.laoyin.dao.makeup.MakeupSettingMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDeadlineDO;
import com.bat.laoyin.service.common.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
@Slf4j
public class MakeupSettingCmdExe extends ServiceCmdImpl<MakeupSettingMapper, MakeupSettingDO> {
    @Resource
    private MakeupSettingDeadlineCmdExe makeupSettingDeadlineCmdExe;

    @Transactional(rollbackFor = Exception.class)
    public void mySave(MakeupSettingCmd aDo) {
        MakeupSettingDO makeupSetting = aDo.getMakeupSetting();
        save(makeupSetting);
        List<MakeupSettingDeadlineDO> settingDeadlines = aDo.getSettingDeadlines();
        settingDeadlines.forEach(makeupSettingDeadlineDO -> {
            makeupSettingDeadlineDO.setMakeupSettingId(makeupSetting.getId());
            Date deadlineTime = makeupSettingDeadlineDO.getDeadlineTime();
            Integer delayMillisecond = makeupSettingDeadlineDO.getDelayMillisecond();
            LocalDateTime dateLocalDateTime = TimeUtils.dateToLocalDateTime(deadlineTime);
            LocalDateTime delayLocalDateTime = dateLocalDateTime.plus(delayMillisecond, ChronoUnit.MILLIS);
            makeupSettingDeadlineDO.setDelayTime(TimeUtils.localDateTimeToDate(delayLocalDateTime));
        });
        makeupSettingDeadlineCmdExe.saveBatch(settingDeadlines);
    }

    @Transactional(rollbackFor = Exception.class)
    public void myUpdateById(MakeupSettingCmd newObj) {
        MakeupSettingDO makeupSetting = newObj.getMakeupSetting();
        updateById(makeupSetting);
        List<MakeupSettingDeadlineDO> settingDeadlines = newObj.getSettingDeadlines();
        // 先删除 在新增截稿时间
        MakeupSettingDeadlineMapper deadlineMapper = makeupSettingDeadlineCmdExe.getBaseMapper();
        LambdaQueryChainWrapper<MakeupSettingDeadlineDO> chainWrapper = new LambdaQueryChainWrapper<>(deadlineMapper);
        List<MakeupSettingDeadlineDO> oldDeadlines =
            chainWrapper.eq(MakeupSettingDeadlineDO::getMakeupSettingId, makeupSetting.getId()).list();
        List<Integer> oldDeadlineIds =
            oldDeadlines.stream().map(MakeupSettingDeadlineDO::getId).collect(Collectors.toList());
        deadlineMapper.deleteBatchIds(oldDeadlineIds);
        settingDeadlines.forEach(makeupSettingDeadlineDO -> {
            makeupSettingDeadlineDO.setMakeupSettingId(makeupSetting.getId());
            Date deadlineTime = makeupSettingDeadlineDO.getDeadlineTime();
            Integer delayMillisecond = makeupSettingDeadlineDO.getDelayMillisecond();
            LocalDateTime dateLocalDateTime = TimeUtils.dateToLocalDateTime(deadlineTime);
            LocalDateTime delayLocalDateTime = dateLocalDateTime.plus(delayMillisecond, ChronoUnit.MILLIS);
            makeupSettingDeadlineDO.setDelayTime(TimeUtils.localDateTimeToDate(delayLocalDateTime));
        });
        makeupSettingDeadlineCmdExe.saveBatch(settingDeadlines);
    }
}
