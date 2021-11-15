package com.bat.laoyin.service.common.handle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/15 20:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessInfoReq {
    private String pdfUrl;
    private String qrStr;
    private String orderStr;
    private String specStr;
    private Integer sort;
}
