package com.bat.laoyin.api.xxljob.api.dto;

import java.io.Serializable;

/**
 * @author Jason(XCH3931399 @ 163.com)
 * @date 2021/6/2 10:55
 */
public class XxlJobRpcCmd implements Serializable {

    /**
     * 执行器
     */
    private Integer jobGroup;
    /**
     * 任务描述
     */
    private String jobDesc;
    /**
     * 负责人
     */
    private String author;
    /**
     * 警告报告邮件地址
     */
    private String alarmEmail;
    /**
     * 调度类型时间配置cron
     */
    private String scheduleConf;
    /**
     * JobHandler,任务执行器
     */
    private String executorHandler;
    /**
     * 任务参数
     */
    private String executorParam;
    /**
     * 子任务ID
     */
    private Integer childJobId;
    /**
     * 创建任务备注
     */
    private String glueRemark;

    public Integer getChildJobId() {
        return childJobId;
    }

    public void setChildJobId(Integer childJobId) {
        this.childJobId = childJobId;
    }

    public Integer getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(Integer jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getScheduleConf() {
        return scheduleConf;
    }

    public void setScheduleConf(String scheduleConf) {
        this.scheduleConf = scheduleConf;
    }

    public String getExecutorHandler() {
        return executorHandler;
    }

    public void setExecutorHandler(String executorHandler) {
        this.executorHandler = executorHandler;
    }

    public String getExecutorParam() {
        return executorParam;
    }

    public void setExecutorParam(String executorParam) {
        this.executorParam = executorParam;
    }

    public String getAlarmEmail() {
        return alarmEmail;
    }

    public void setAlarmEmail(String alarmEmail) {
        this.alarmEmail = alarmEmail;
    }

    public String getGlueRemark() {
        return glueRemark;
    }

    public void setGlueRemark(String glueRemark) {
        this.glueRemark = glueRemark;
    }
}
