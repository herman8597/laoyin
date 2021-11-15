package com.bat.laoyin.api.makeup.dto.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/13 14:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "DispatchListDTO对象", description = "发货单封装类")
public class DispatchListRespDTO extends PdfFileBaseRespDTO {
    @ApiModelProperty(value = "打印类型 1 发货单 2 运单")
    private Integer fileType;
    @ApiModelProperty(value = "扫描次数")
    private Integer scanSum;
    @ApiModelProperty(value = "标签文件Url")
    private String labelPdfUrl;
    @ApiModelProperty(value = "发货单编码")
    private String dispatchListCode;
}
