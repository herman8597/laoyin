package com.bat.laoyin.job;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bat.laoyin.api.common.LaoYinException;
import com.bat.laoyin.api.makeup.MakeupSettingDeadlineService;
import com.bat.laoyin.api.makeup.MakeupTaskService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lim
 * @description: 截稿时间 时间更新任务
 * 
 *               高可用 多实例 加锁避免 多次执行
 * @date: 2021/9/17 10:05
 */
@Component
@Slf4j
public class DeadLineJob {

    @Resource
    private MakeupSettingDeadlineService service;

    @Resource
    private MakeupTaskService makeupTaskService;

    @Scheduled(cron = JobCorns.DEAD_LINE_JOB)
    public void doJob() {
        log.info("定时任务：重新纠正截稿时间=====");
        service.fixDeadlineTime();
    }

    /**
     * 截稿任务
     *
     * @throws Exception
     */
    @XxlJob("makeupDeadLineJobHandler")
    public void makeupDeadLineJobHandler() throws Exception {
        log.info("截稿任务开始执行");
        XxlJobHelper.log("截稿任务开始");
        // 执行参数
        String param = XxlJobHelper.getJobParam();
        if (param != null) {
            try {
                Map<String, Object> map = JSON.parseObject(param, Map.class);
                Integer makeupTaskId = (Integer)map.get("makeupTaskId");
                makeupTaskService.doDeadLine(makeupTaskId);
            } catch (LaoYinException e) {
                log.info(e.getCode());
                XxlJobHelper.log(e.getCode());
                e.printStackTrace();
            } catch (Exception e) {
                log.info(e.getLocalizedMessage());
                XxlJobHelper.log(e.getLocalizedMessage());
                e.printStackTrace();
            }
        } else {
            log.info("没有参数，无法截稿");
            XxlJobHelper.log("没有参数，无法截稿");
        }
    }
}
