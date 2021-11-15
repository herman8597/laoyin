package com.bat.laoyin.api.common;

import lombok.Data;

/**
 * @author Jason(XCH3931399 @ 163.com)
 * @date 2021/4/2 10:32
 */
@Data
public class LaoYinException extends RuntimeException {

    private String code;
    private String msg;

    public static LaoYinException buildException(String errCode) {
        LaoYinException exception = new LaoYinException();
        exception.setCode(errCode);
        return exception;
    }

    public static LaoYinException buildException(String errCode, String msg) {
        LaoYinException exception = new LaoYinException();
        exception.setCode(errCode);
        exception.setMsg(msg);
        return exception;
    }

}
