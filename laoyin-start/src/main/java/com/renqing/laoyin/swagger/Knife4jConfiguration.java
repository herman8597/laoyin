package com.bat.laoyin.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/8 5:29
 */
@Configuration
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfoBuilder()
            // .title("swagger-bootstrap-ui-demo RESTful APIs")
            .description("# laoyin RESTful APIs").termsOfServiceUrl("http://www.laoyin.com/")
            .contact(new Contact("lim", "bat.com", "xx@qq.com")).version("1.0.0").build())
            // 分组名称
            .groupName("1.X版本").select()
            // 这里指定Controller扫描包路径
            .apis(RequestHandlerSelectors.basePackage("com.bat.laoyin.web")).paths(PathSelectors.any()).build();
        return docket;
    }
}