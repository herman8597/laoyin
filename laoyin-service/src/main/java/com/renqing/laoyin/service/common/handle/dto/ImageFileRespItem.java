package com.bat.laoyin.service.common.handle.dto;

import java.awt.image.BufferedImage;

import lombok.Data;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/15 20:43
 */
@Data
public class ImageFileRespItem {
    // // 最终的 png 图片
    // private ByteArrayOutputStream outputStream;
    // // 图片 加 pdf 的宽
    // private float pdfWidth;
    // // 图片 加 pdf 的高
    // private float pdfHeight;
    // 内部处理
    private float x;
    private float y;
    private BufferedImage bufferedImage;
    private BusinessInfoReq info;
}
