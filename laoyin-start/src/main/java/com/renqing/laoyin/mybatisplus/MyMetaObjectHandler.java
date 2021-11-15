package com.bat.laoyin.mybatisplus;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.bat.laoyin.service.user.UserServiceImpl;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/11 23:09
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 一但异步 HttpServletRequest 线程绑定失效 这里就会报错 一定要注意
     */
    @Resource
    private HttpServletRequest request;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        Integer tenantId = 1;
        int userId = 1;
        try {
            String userIdStr = request.getHeader("ly-userid");
            String token = request.getHeader("ly-token");
            userId = Integer.parseInt(userIdStr);
            Claims claims = UserServiceImpl.getClaims(token);
            tenantId = claims.get("tenantId", Integer.TYPE);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        Date date = new Date();
        this.strictInsertFill(metaObject, "createdBy", Integer.class, userId);
        this.strictInsertFill(metaObject, "createdAt", Date.class, date);
        this.strictInsertFill(metaObject, "updatedBy", Integer.class, userId);
        this.strictInsertFill(metaObject, "updatedAt", Date.class, date);
        this.strictInsertFill(metaObject, "tenantId", Integer.class, tenantId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        Integer tenantId = 1;
        int userId = 1;
        try {
            String userIdStr = request.getHeader("ly-userid");
            String token = request.getHeader("ly-token");
            userId = Integer.parseInt(userIdStr);
            Claims claims = UserServiceImpl.getClaims(token);
            tenantId = claims.get("tenantId", Integer.TYPE);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        Date date = new Date();
        this.strictInsertFill(metaObject, "updatedBy", Integer.class, userId);
        this.strictInsertFill(metaObject, "updatedAt", Date.class, date);
        this.strictInsertFill(metaObject, "tenantId", Integer.class, tenantId);
    }
}