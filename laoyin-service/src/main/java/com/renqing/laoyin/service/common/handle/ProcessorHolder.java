package com.bat.laoyin.service.common.handle;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/15 21:32
 */
@Component
public class ProcessorHolder implements ApplicationContextAware {

    Map<String, AbstractPdfProcessor> map = new HashMap<>();
    private ApplicationContext app;

    @PostConstruct
    public void init() {
        map.put("blk", app.getBean("UV132PdfProcessor", AbstractPdfProcessor.class));
        map.put("tpu", app.getBean("UV132PdfProcessor", AbstractPdfProcessor.class));
        map.put("rsh6", app.getBean("DyeSublimation6PdfProcessor", AbstractPdfProcessor.class));
        map.put("rsh8", app.getBean("DyeSublimation8PdfProcessor", AbstractPdfProcessor.class));
    }

    public AbstractPdfProcessor getProcessor(String key) {
        return map.get(key);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.app = applicationContext;
    }
}
