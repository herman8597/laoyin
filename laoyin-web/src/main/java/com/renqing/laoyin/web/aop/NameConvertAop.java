package com.bat.laoyin.web.aop;

import java.util.List;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bat.laoyin.api.common.BaseDTO;
import com.bat.laoyin.api.user.UserService;
import com.bat.laoyin.dao.base.BaseDO;
import com.bat.laoyin.web.base.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/15 9:57
 */
@Aspect
@Component
@Slf4j
public class NameConvertAop {

    @Resource
    private UserService userService;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {}

    @Around("getMapping()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        if (proceed instanceof Response) {
            Object data = ((Response<?>)proceed).getData();
            if (data instanceof IPage) {
                convertNameList(((IPage)data).getRecords());
            } else if (data instanceof List) {
                convertNameList((List)data);
            } else if (data instanceof BaseDTO || data instanceof BaseDO) {
                convertName(data);
            }
        }
        return proceed;
    }

    private void convertNameList(List list) {
        list.forEach(this::convertName);
    }

    private void convertName(Object o) {
        if (o instanceof BaseDO) {
            BaseDO baseDO = (BaseDO)o;
            baseDO.setCreatedName(userService.getUserNameById(baseDO.getCreatedBy()));
            baseDO.setUpdatedName(userService.getUserNameById(baseDO.getUpdatedBy()));
        } else if (o instanceof BaseDTO) {
            BaseDTO baseDTO = (BaseDTO)o;
            baseDTO.setCreatedName(userService.getUserNameById(baseDTO.getCreatedBy()));
            baseDTO.setUpdatedName(userService.getUserNameById(baseDTO.getUpdatedBy()));
        }
    }

}
