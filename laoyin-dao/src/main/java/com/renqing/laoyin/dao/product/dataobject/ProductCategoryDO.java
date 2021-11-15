package com.bat.laoyin.dao.product.dataobject;

import java.util.List;

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
 * 产品类别表
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("product_category")
@ApiModel(value = "ProductCategoryDO对象", description = "产品类别表")
public class ProductCategoryDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "父id", required = true)
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "产品分类中文", required = true)
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "产品分类英文")
    @TableField("name_en")
    private String nameEn;

    @ApiModelProperty(value = "是否还有下一级 0否 1是", required = true)
    @TableField("have_next")
    private Short haveNext;

    @ApiModelProperty(value = "下级内容")
    @TableField(value = "have_next", exist = false)
    private List<ProductCategoryDO> children;

}
