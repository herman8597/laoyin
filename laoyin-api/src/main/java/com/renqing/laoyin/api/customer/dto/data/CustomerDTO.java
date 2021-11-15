package com.bat.laoyin.api.customer.dto.data;

import com.bat.laoyin.api.common.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 租户客户表
 * </p>
 *
 * @author lim
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "CustomerDTO对象", description = "租户客户表")
public class CustomerDTO extends BaseDTO {

    @ApiModelProperty(value = "	多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "客户简称")
    private String name;

    @ApiModelProperty(value = "客户公司名")
    private String company;

    @ApiModelProperty(value = "联系人id（租户管理员）")
    private Integer contactId;

    @ApiModelProperty(value = "联系人姓名（冗余）")
    private String contactName;

    @ApiModelProperty(value = "联系人电话（冗余）")
    private String contactPhone;
}
