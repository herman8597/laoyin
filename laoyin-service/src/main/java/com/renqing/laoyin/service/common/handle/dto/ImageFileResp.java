package com.bat.laoyin.service.common.handle.dto;

import java.awt.image.BufferedImage;
import java.util.List;

import lombok.Data;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/15 20:43
 */
@Data
public class ImageFileResp {
    /**
     * 主画布 最大的画布
     */
    private BufferedImage mainBufferedImage;
    /**
     * 每一项的画布信息
     */
    private List<ImageFileRespItem> items;
}
