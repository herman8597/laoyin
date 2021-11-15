package com.bat.laoyin.api.makeup.dto.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/11 21:12
 */
@Data
public class PdfFileBaseRespDTO {
    @ApiModelProperty(value = "跨域打印地址")
    private String crossPrintUrl;
    @ApiModelProperty(value = "非跨域打印地址(pdf文件地址)")
    private String noCrossPrintUrl;
}
