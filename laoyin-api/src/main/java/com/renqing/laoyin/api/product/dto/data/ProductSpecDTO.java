package com.bat.laoyin.api.product.dto.data;

import com.bat.laoyin.api.common.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Accessors(chain = true)
@ApiModel(value = "ProductSpecDTO对象", description = "产品规格表")
public class ProductSpecDTO extends BaseDTO {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "产品品类id")
    private Integer productFamilyId;

    @ApiModelProperty(value = "规格名1")
    private String label1;

    @ApiModelProperty(value = "规格值1")
    private String value1;

    @ApiModelProperty(value = "规格名2")
    private String label2;

    @ApiModelProperty(value = "规格值2")
    private String value2;

    @ApiModelProperty(value = "规格名3")
    private String label3;

    @ApiModelProperty(value = "规格值3")
    private String value3;
}
