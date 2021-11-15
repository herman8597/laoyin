package com.bat.laoyin.service.common.utils.file;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

import com.itextpdf.kernel.geom.PageSize;

/**
 * @author: lim
 * @description: 单位工具类
 * @date: 2021/10/14 21:39
 */
public class UnitUtils {

    public static int DPI300 = 300;

    public static float DPI300_SCALE = 4.1666665F;

    public static String PNG_FORMAT_NAME = "png";

    public static PageSize mm2PageSize(float width, float height) {
        return new PageSize(mm2PDFUnit(width), mm2PDFUnit(height));
    }

    public static PDRectangle mm2PDRectangle(float width, float height) {
        return new PDRectangle(mm2PDFUnit(width), mm2PDFUnit(height));
    }

    public static float mm2PDFUnit(float mm) {
        return 595.27563F / 210 * mm;
    }

    public static float PDFUnit2mm(float unit) {
        return unit * (210 / 595.27563F);
    }

    public static float mm2px300(float mm) {
        return mm2PDFUnit(mm) * DPI300_SCALE;
    }

    public static float px2PDFUnit(float px) {
        return px / DPI300_SCALE;
    }
}
