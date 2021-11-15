package com.bat.laoyin.interceptor;

import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.bat.laoyin.api.common.ErrorCode;
import com.bat.laoyin.api.customer.CustomerService;
import com.bat.laoyin.dao.customer.dataobject.CustomerDO;
import com.bat.laoyin.web.base.Response;

/**
 * @author: lim
 * @description: 客户鉴权拦截器
 *
 *               纠结了老半天 在拦截器做 还是AOP做，在拦截器也有弊端
 * @date: 2021/9/9 18:49
 */
@Component
public class CustomerAuthInterceptor implements HandlerInterceptor {

    public static String InterceptorPath = "/*/third/";

    @Resource
    private CustomerService customerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        try {
            String appKeys = request.getParameterValues("appKey")[0];
            String appSecret = request.getParameterValues("appSecret")[0];
            LambdaQueryChainWrapper<CustomerDO> chainWrapper =
                new LambdaQueryChainWrapper<>(customerService.getBaseMapper());
            CustomerDO entity =
                chainWrapper.eq(CustomerDO::getAppKey, appKeys).eq(CustomerDO::getAppSecret, appSecret).one();
            if (entity != null) {
                request.getSession().setAttribute("customerId", entity.getId());
                request.getSession().setAttribute("customerName", entity.getName());
                return true;
            }
        } catch (Exception ignored) {
        }
        Response buildFailure =
            Response.buildFailure(ErrorCode.B_APP_KEY_OR_APP_SECRET_IS_INVALID, "appKey或者appSecret已经失效");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().println(JSON.toJSONString(buildFailure));
        return false;
    }
}
