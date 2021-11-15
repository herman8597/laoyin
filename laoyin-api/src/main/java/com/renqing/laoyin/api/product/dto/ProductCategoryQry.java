package com.bat.laoyin.api.product.dto;

import com.bat.laoyin.api.common.BasePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Accessors(chain = true)
@ApiModel(value = "ProductCategoryDTO对象", description = "产品类别表")
public class ProductCategoryQry extends BasePage {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "父id")
    private Integer parentId;

    @ApiModelProperty(value = "产品分类中文")
    private String name;

    @ApiModelProperty(value = "产品分类英文")
    private String nameEn;

    @ApiModelProperty(value = "是否还有下一级 0否 1是")
    private Short haveNext;
}
