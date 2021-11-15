package com.bat.laoyin.api.makeup.dto.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: lim
 * @description: 领料单行项 并不是真实存在的表 所以My开头
 * @date: 2021/9/23 15:52
 */
@Data
public class MaterialRequisitionItemRespDTO {
    @ApiModelProperty(value = "产品序号")
    private Integer rowNum;
    @ApiModelProperty(value = "产品编码")
    private String code;
    @ApiModelProperty(value = "产品名称")
    private String name;
    @ApiModelProperty(value = "产品规格")
    private String productSpecName;
    @ApiModelProperty(value = "单位")
    private String unitName;
    @ApiModelProperty(value = "产品高度(mm)")
    private Integer height;
    @ApiModelProperty(value = "领料数量")
    private Integer countNum;
}
