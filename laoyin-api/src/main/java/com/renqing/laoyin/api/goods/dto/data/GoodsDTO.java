package com.bat.laoyin.api.goods.dto.data;

import com.bat.laoyin.api.common.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "GoodsDTO对象", description = "商品表")
public class GoodsDTO extends BaseDTO {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "产品code（商品:产品 n:1）")
    private String productCode;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "产品规格名称")
    private String productSpec;

    @ApiModelProperty(value = "产品单位")
    private String productUnitName;

    @ApiModelProperty(value = "sku")
    private String sku;

    @ApiModelProperty(value = "第三方商品名称（参与sku唯一校验）")
    private String name;

    @ApiModelProperty(value = "第三方商品编码（参与sku唯一校验）")
    private String code;

    @ApiModelProperty(value = "第三方商品规格（参与sku唯一校验）")
    private String spec;

    @ApiModelProperty(value = "第三方商品单位")
    private String unitName;

    @ApiModelProperty(value = "发货平台编码（参与sku唯一校验）")
    private String platformCode;

    @ApiModelProperty(value = "配送方式id")
    private Integer distributionModeId;

    @ApiModelProperty(value = "第三方商品备注")
    private String remark;
}
