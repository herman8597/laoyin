package com.bat.laoyin.swagger;

import java.util.*;

import org.springframework.context.annotation.Bean;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

// @Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any()).build()
            // 全局请求参数设置
            // .globalRequestParameters(getRequestParameter())
            // 支持的通讯协议集合
            .protocols(newHashSet("https", "http"));

        // 授权信息设置，必要的header token等认证信息
        // .securitySchemes(securitySchemes())
        //
        // // 授权信息全局应用
        // .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("微服务接口文档").description("")
            .contact(new Contact("bat", "https://bat.com", "info@bat.hk")).version("1.0").build();
    }

    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("authorization", "authorization", In.HEADER.toValue()));
        return Collections.unmodifiableList(apiKeys);
    }

    /**
     * 添加全局请求参数
     *
     * @return
     */
    private List<RequestParameter> getRequestParameter() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder().name("language-currency")
            .description("平台访问语言和币种(不填默认为zh_CNY,zh_USD,en_CNY,zh_USD)").required(false).in(ParameterType.HEADER)
            .build());
        parameters.add(new RequestParameterBuilder().name("token").description("鉴权token（非登录接口必填）").required(false)
            .in(ParameterType.HEADER).build());
        return parameters;
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(SecurityContext.builder()
            .securityReferences(Collections.singletonList(
                new SecurityReference("language", new AuthorizationScope[] {new AuthorizationScope("global", "")})))
            .build());
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }
}