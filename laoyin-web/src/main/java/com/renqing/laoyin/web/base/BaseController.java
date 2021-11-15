package com.bat.laoyin.web.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    private HttpServletRequest request;

    protected String getUserId() {
        return request.getHeader("userId");
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
