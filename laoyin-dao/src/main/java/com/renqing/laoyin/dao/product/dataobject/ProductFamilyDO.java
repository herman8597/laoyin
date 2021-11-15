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
 * 产品品类(产品簇)表
 * </p>
 *
 * @author lim
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("product_family")
@ApiModel(value = "ProductFamilyDO对象", description = "产品品类(产品簇)表")
public class ProductFamilyDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "品类名称", required = true)
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "品类编码", required = true)
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "产品类别id", required = true)
    @TableField("product_category_id")
    private Integer productCategoryId;

    @ApiModelProperty(value = "计数单位", required = true)
    @TableField("unit_name")
    private String unitName;

    @ApiModelProperty(value = "品类说明")
    @TableField("family_desc")
    private String familyDesc;

}
