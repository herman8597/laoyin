package com.bat.laoyin.web.test;

import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.bat.laoyin.api.xxljob.service.XxlJobService;
import com.bat.laoyin.web.base.Response;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/22 15:01
 */
@RestController
public class TestController {

    @Resource
    private XxlJobService xxlJobService;

    // @RequestMapping("test")
    // public Response test() {
    // List<XxlJobRpcCmd> list = new ArrayList();
    // XxlJobRpcCmd cmd = new XxlJobRpcCmd();
    // cmd.setAuthor("XXL");
    // cmd.setExecutorHandler("makeupDeadLineJobHandler");
    // cmd.setJobDesc("拼版截稿任务1");
    // cmd.setScheduleConf("0 0 0 * * ?");
    // cmd.setJobGroup(1);
    // list.add(cmd);
    // xxlJobService.xxlJobAdd(list);
    // return Response.buildSuccess();
    // }

    @RequestMapping("test")
    public Response test() {
        final String TEMPLATE_PREFIX = "doc/temp/";
        final String TEMPLATE_SUFFIX = ".html";

        try {
            ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
            resolver.setPrefix(TEMPLATE_PREFIX);
            resolver.setSuffix(TEMPLATE_SUFFIX);
            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(resolver);
            // 填充数据
            Context context = new Context();
            // context.setVariable("qrImgPath", "C:\\Users\\lx\\Desktop\\demo1\\qr.png");
            // context.setVariable("taskNo", "B20210922180120899");
            // 渲染模板生成静态
            FileWriter writer = new FileWriter("./index.html");
            templateEngine.process("index", context, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.buildSuccess();
    }
}
