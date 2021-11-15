package com.bat.laoyin.api.sendgoods.dto.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 发货单表
 * </p>
 *
 * @author lim
 * @since 2021-10-09
 */
@Data
@ApiModel(value = "DispatchListDTO对象", description = "发货单订单行项配货行项")
public class DispatchListItemItemDTO {

    @ApiModelProperty(value = "配货码")
    private String distributionCode;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "sku编码")
    private String sku;

    @ApiModelProperty(value = "产品规格名称")
    private String productSpecName;

    @ApiModelProperty(value = "产品品牌名称")
    private String unitName;

    @ApiModelProperty(value = "商品数量")
    private Integer itemCount;
}
