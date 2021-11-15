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
 * 产品规格表
 * </p>
 *
 * @author lim
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("product_spec")
@ApiModel(value = "ProductSpecDO对象", description = "产品规格表")
public class ProductSpecDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "产品品类id", required = true)
    @TableField("product_family_id")
    private Integer productFamilyId;

    @ApiModelProperty(value = "规格名1")
    @TableField("label1")
    private String label1;

    @ApiModelProperty(value = "规格值1")
    @TableField("value1")
    private String value1;

    @ApiModelProperty(value = "规格名2")
    @TableField("label2")
    private String label2;

    @ApiModelProperty(value = "规格值2")
    @TableField("value2")
    private String value2;

    @ApiModelProperty(value = "规格名3")
    @TableField("label3")
    private String label3;

    @ApiModelProperty(value = "规格值3")
    @TableField("value3")
    private String value3;

    public String getSimpleSpecName() {
        // 'StringBuilder' can be replaced with 'String'
        return getValue1() + " " + getValue2() + " " + getValue3();
    }

    public String getFullSpecName() {
        // 'StringBuilder' can be replaced with 'String'
        return getLabel1() + " " + getValue1() + " " + getLabel2() + " " + getValue2() + " " + getLabel3() + " "
            + getValue3();
    }
}
