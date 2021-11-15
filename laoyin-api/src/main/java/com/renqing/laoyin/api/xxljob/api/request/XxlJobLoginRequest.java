package com.bat.laoyin.api.xxljob.api.request;

import lombok.Data;

/**
 * @author Jason(XCH3931399 @ 163.com)
 * @date 2021/6/2 11:54
 */
@Data
public class XxlJobLoginRequest {
    /**
     * 调度中心登录用户名
     */
    private String userName;
    /**
     * 调度中心登录密码
     */
    private String password;
    private String ifRemember;
}
