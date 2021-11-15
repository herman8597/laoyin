package com.bat.laoyin.api.product.dto.data;

import com.bat.laoyin.api.common.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author lim
 * @since 2021-08-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ProductDTO对象", description = "产品表")
public class ProductDTO extends BaseDTO {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "产品名称")
    private String name;

    @ApiModelProperty(value = "产品编码")
    private String code;

    @ApiModelProperty(value = "产品品类分类id")
    private Integer productCategoryId;

    @ApiModelProperty(value = "产品品类分类名称")
    private String productCategoryName;

    @ApiModelProperty(value = "产品品牌id")
    private Integer productBrandId;

    @ApiModelProperty(value = "产品品牌名称")
    private String productBrandName;

    @ApiModelProperty(value = "产品品类id")
    private Integer productFamilyId;

    @ApiModelProperty(value = "产品品类名称")
    private String productFamilyName;

    @ApiModelProperty(value = "产品规格id")
    private Integer productSpecId;

    @ApiModelProperty(value = "产品规格名称")
    private String productSpecName;

    @ApiModelProperty(value = "产品单位（默认取规格单位）")
    private String unitName;

    @ApiModelProperty(value = "重量(克)")
    private Integer weight;

    @ApiModelProperty(value = "产品尺寸长(mm)")
    private Integer length;

    @ApiModelProperty(value = "产品尺寸高(mm)")
    private Integer height;

    @ApiModelProperty(value = "产品尺寸宽(mm)")
    private Integer width;
}
