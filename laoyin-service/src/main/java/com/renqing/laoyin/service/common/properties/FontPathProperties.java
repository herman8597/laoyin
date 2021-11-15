package com.bat.laoyin.service.common.properties;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.bat.laoyin.service.common.utils.file.FileUtils;

import lombok.Data;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/14 20:49
 */
@Data
@Component
@ConfigurationProperties(prefix = "resources")
public class FontPathProperties {
    private List<String> itext7Font = new ArrayList<>();
    private List<String> pdfboxFont = new ArrayList<>();

    public String getItext7FontStr() {
        return FileUtils.getFontStr(itext7Font);
    }

    public File getPdfBoxFontFile() {
        return FileUtils.getFontFile(pdfboxFont);
    }
}
