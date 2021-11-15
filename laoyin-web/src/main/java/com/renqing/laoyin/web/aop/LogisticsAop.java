package com.bat.laoyin.web.aop;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.bat.laoyin.api.logistics.dto.KDNOrderOnlineResp;
import com.bat.laoyin.web.aop.utils.HtmlStrHandler;
import com.bat.laoyin.web.aop.utils.ZTOHtmlStrHandlerImpl;
import com.bat.laoyin.web.base.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/14 17:48
 */
@Aspect
@Component
@Slf4j
public class LogisticsAop {

    private static Map<String, HtmlStrHandler> map = new HashMap<>();
    static {
        map.put("ZTO", new ZTOHtmlStrHandlerImpl());
    }

    @Pointcut("execution( * com.bat.laoyin.web.print..PrintTaskController.getWaybill(..))")
    public void getWaybill() {}

    @Around("getWaybill()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        Response response = null;
        try {
            response = (Response)proceed;
            if (response.isSuccess()) {
                KDNOrderOnlineResp data = (KDNOrderOnlineResp)response.getData();
                if (StringUtils.isNotBlank(data.getPrintTemplate())) {
                    HtmlStrHandler htmlStrHandler = map.get(data.getOrder().getShipperCode());
                    data.setPrintTemplate(htmlStrHandler.convert(data.getPrintTemplate()));
                    return data;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return proceed;
        }
    }
}
