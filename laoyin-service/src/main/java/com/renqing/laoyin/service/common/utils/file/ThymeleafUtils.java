package com.bat.laoyin.service.common.utils.file;

import static com.bat.laoyin.service.common.utils.file.FileUtils.mkdirs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.bat.laoyin.service.common.constants.SystemConstant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/24 15:22
 */
public class ThymeleafUtils {

    private static final Logger log = LoggerFactory.getLogger(ThymeleafUtils.class);

    private static final String TEMPLATE_PREFIX = "doc/temp/";
    private static final String TEMPLATE_SUFFIX = ".html";

    public static File createHtml(Map<String, Object> params, String filePath, String templateName) throws IOException {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix(TEMPLATE_PREFIX);
        resolver.setSuffix(TEMPLATE_SUFFIX);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        // 填充数据
        Context context = new Context();
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                context.setVariable(entry.getKey(), entry.getValue());
            }
        }
        // 渲染模板生成静态
        String path = SystemConstant.TEMP_PATH + filePath;
        File file = mkdirs(path);
        FileWriter fileWriter = new FileWriter(file);
        templateEngine.process(templateName, context, fileWriter);
        log.info("生成静态html path:{}", file.getAbsolutePath());
        return file;
    }
}
