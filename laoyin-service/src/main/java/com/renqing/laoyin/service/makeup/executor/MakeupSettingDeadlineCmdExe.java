package com.bat.laoyin.service.makeup.executor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.makeup.MakeupSettingDeadlineMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDeadlineDO;
import com.bat.laoyin.service.common.utils.TimeUtils;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class MakeupSettingDeadlineCmdExe extends ServiceCmdImpl<MakeupSettingDeadlineMapper, MakeupSettingDeadlineDO> {
    /**
     * 小于当天0点 更新到当天
     */
    public void fixDeadlineTime() {
        LambdaQueryChainWrapper<MakeupSettingDeadlineDO> chainWrapper = new LambdaQueryChainWrapper<>(getBaseMapper());
        Date dateTime = TimeUtils.localDateTimeToDate(LocalDate.now().atStartOfDay());
        List<MakeupSettingDeadlineDO> deadlines =
            chainWrapper.lt(MakeupSettingDeadlineDO::getDelayTime, dateTime).list();
        deadlines.forEach(makeupSettingDeadlineDO -> {
            // 截稿时间
            Date deadlineTime = makeupSettingDeadlineDO.getDeadlineTime();
            LocalDateTime deadlineLocalDateTime = TimeUtils.dateToLocalDateTime(deadlineTime);
            LocalDateTime delayLocalDateTime =
                deadlineLocalDateTime.plus(makeupSettingDeadlineDO.getDelayMillisecond(), ChronoUnit.MILLIS);
            makeupSettingDeadlineDO.setDelayTime(TimeUtils.localDateTimeToDate(delayLocalDateTime));
        });
        updateBatchById(deadlines);
    }

}
