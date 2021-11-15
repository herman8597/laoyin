package com.bat.laoyin.api.makeup.dto.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Lim
 * @date 2021/9/27 21:22
 */
@Data
@ApiModel(value = "DistributionCodeDTO对象", description = "配货码封装类")
public class DistributionCodeItemRespDTO {
    @ApiModelProperty(value = "配货码")
    private String distributionCode;

    @ApiModelProperty(value = "配货码索引")
    private Integer distributionCodeIndex;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "生产系统订单号")
    private String orderNo;

    @ApiModelProperty(value = "第三方系统订单号")
    private String thirdPartyNo;

    @ApiModelProperty(value = "第三方系统规格名称")
    private String thirdPartySpecName;

    @ApiModelProperty(value = "数量")
    private Integer itemCount;

    @ApiModelProperty(value = "效果图url")
    private String effectFilePath;

    @ApiModelProperty(value = "当前物料在某个订单中的索引（分子）")
    private Integer materialIndex;

    @ApiModelProperty(value = "某个订单的物料总数（分母）")
    private Integer materialSum;

}
