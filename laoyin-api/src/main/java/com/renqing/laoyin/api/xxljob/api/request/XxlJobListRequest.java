package com.bat.laoyin.api.xxljob.api.request;

import lombok.Data;

/**
 * @author Jason(XCH3931399 @ 163.com)
 * @date 2021/6/2 11:54
 */
@Data
public class XxlJobListRequest {
    /**
     * 执行器id
     */
    private Integer jobGroup;
    /**
     * 任务描述
     */
    private String jobDesc;
    /**
     * jobHandler
     */
    private String executorHandler;
    /**
     * 状态
     */
    private Integer triggerStatus = -1;
}
