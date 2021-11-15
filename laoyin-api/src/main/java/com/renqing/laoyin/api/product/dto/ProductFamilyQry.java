package com.bat.laoyin.api.product.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bat.laoyin.api.common.BasePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Accessors(chain = true)
@ApiModel(value = "ProductFamilyDTO对象", description = "产品品类(产品簇)表")
public class ProductFamilyQry extends BasePage {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "品类名称")
    private String name;

    @ApiModelProperty(value = "品类编码")
    private String code;

    @ApiModelProperty(value = "产品类别id")
    private Integer productCategoryId;

    @ApiModelProperty(value = "产品名称（查询字段 右模糊查询）")
    private String productName;

    @ApiModelProperty(value = "计数单位")
    private String unitName;

    @ApiModelProperty(value = "品类说明")
    private String familyDesc;

    @ApiModelProperty(value = "创建时间开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdStartTime;

    @ApiModelProperty(value = "创建时间结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdEndTime;
}
