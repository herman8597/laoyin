package com.bat.laoyin.dao.product.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bat.laoyin.dao.base.BaseDO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("product")
@ApiModel(value = "ProductDO对象", description = "产品表")
public class ProductDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = false, hidden = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "产品名称", required = true)
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "产品编码", required = true)
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "产品品类分类id", required = false)
    @TableField("product_category_id")
    private Integer productCategoryId;

    @ApiModelProperty(value = "产品品类分类名称", required = false)
    @TableField("product_category_name")
    private String productCategoryName;

    @ApiModelProperty(value = "产品品牌id", required = true)
    @TableField("product_brand_id")
    private Integer productBrandId;

    @ApiModelProperty(value = "产品品牌名称", required = false)
    @TableField("product_brand_name")
    private String productBrandName;

    @ApiModelProperty(value = "产品品类id", required = true)
    @TableField("product_family_id")
    private Integer productFamilyId;

    @ApiModelProperty(value = "产品品类名称", required = false)
    @TableField("product_family_name")
    private String productFamilyName;

    @ApiModelProperty(value = "产品规格id", required = true)
    @TableField("product_spec_id")
    private Integer productSpecId;

    @ApiModelProperty(value = "产品规格名称", required = false)
    @TableField("product_spec_name")
    private String productSpecName;

    @ApiModelProperty(value = "产品单位（默认取规格单位）", required = true)
    @TableField("unit_name")
    private String unitName;

    @ApiModelProperty(value = "重量(克)", required = true)
    @TableField("weight")
    private Integer weight;

    @ApiModelProperty(value = "产品尺寸长(mm)", required = true)
    @TableField("length")
    private Integer length;

    @ApiModelProperty(value = "产品尺寸高(mm)", required = true)
    @TableField("height")
    private Integer height;

    @ApiModelProperty(value = "产品尺寸宽(mm)", required = true)
    @TableField("width")
    private Integer width;

    @ApiModelProperty(value = "绑定拼版设置标志位 0全部 1绑定 2未绑定", hidden = true)
    @TableField(exist = false)
    private Short bindMakeUpFlag;
}
