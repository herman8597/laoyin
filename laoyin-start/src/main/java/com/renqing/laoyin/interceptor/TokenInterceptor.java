package com.bat.laoyin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 前台租户接口拦截器 TODO
 */

@Component
public class TokenInterceptor implements HandlerInterceptor {

    public static String InterceptorPath = "/";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
        throws Exception {
        if (httpServletRequest.getRequestURI().contains("login")) {
            return true;
        }
        return true;
    }
}
