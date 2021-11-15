package com.bat.laoyin.service.common.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.layout.HtmlPageBreak;
import com.itextpdf.html2pdf.css.media.MediaDeviceDescription;
import com.itextpdf.html2pdf.css.media.MediaType;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.bat.laoyin.service.common.constants.SystemConstant;
import com.bat.laoyin.service.common.properties.FontPathProperties;

/**
 * @author: lim
 * @description: Itext7 工具类
 * @date: 2021/9/24 16:53
 */
@Component
public class PdfItext7Utils {

    private static final Logger log = LoggerFactory.getLogger(PdfItext7Utils.class);

    @Resource
    private FontPathProperties fontPath;

    public  void convertToDocument(File htmlFile, String filePath) throws IOException {
        convertToDocument(htmlFile, filePath, PageSize.A4, null);
    }

    /**
     * 单个html
     * 
     * @param htmlFile
     * @param filePath
     * @param pageSize
     * @param margin
     * @throws IOException
     */
    public  void convertToDocument(File htmlFile, String filePath, PageSize pageSize, String margin)
        throws IOException {
        convertToDocument(Collections.singletonList(htmlFile), filePath, pageSize, margin);
    }

    /**
     * 多个html
     * 
     * @param htmlFiles
     * @param filePath
     * @param pageSize
     * @param margin
     * @throws IOException
     */
    public  void convertToDocument(List<File> htmlFiles, String filePath, PageSize pageSize, String margin)
        throws IOException {
        ConverterProperties props = new ConverterProperties();
        DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false, false, false);
        defaultFontProvider.addFont(fontPath.getItext7FontStr());
        props.setFontProvider(defaultFontProvider);
        props.setBaseUri(htmlFiles.get(0).getParent());
        MediaDeviceDescription mediaDeviceDescription = new MediaDeviceDescription(MediaType.PRINT);
        props.setMediaDeviceDescription(mediaDeviceDescription);
        File file = new File(SystemConstant.TEMP_PATH + filePath);
        PdfDocument pdf = new PdfDocument(new PdfWriter(file));
        pdf.setTagged();
        Document document = new Document(pdf, pageSize);
        if (StringUtils.isNotBlank(margin)) {
            String[] split = margin.split(",");
            if (split.length == 4) {
                document.setMargins(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2]),
                    Float.parseFloat(split[3]));
            }
        }
        // "topMargin,rightMargin,bottomMargin,leftMargin"
        for (File htmlFile : htmlFiles) {
            List<IElement> elements = HtmlConverter.convertToElements(new FileInputStream(htmlFile), props);
            for (IElement element : elements) {
                if (element instanceof HtmlPageBreak) {
                    document.add((HtmlPageBreak)element);
                } else {
                    document.add((IBlockElement)element);
                }
            }
        }
        document.close();
        log.info("生成pdf path:{}", file.getAbsolutePath());
    }
}