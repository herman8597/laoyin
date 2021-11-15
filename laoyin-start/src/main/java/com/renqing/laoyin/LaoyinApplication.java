package com.bat.laoyin;

// import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;

@SpringBootApplication
// @DubboComponentScan
@EnableMethodCache(basePackages = "com.bat.laoyin")
@EnableCreateCacheAnnotation
@EnableScheduling
@MapperScan("com.bat.laoyin.dao")
public class LaoyinApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaoyinApplication.class, args);
    }

}
