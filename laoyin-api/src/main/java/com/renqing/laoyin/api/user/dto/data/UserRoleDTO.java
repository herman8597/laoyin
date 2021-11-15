package com.bat.laoyin.api.user.dto.data;

import com.bat.laoyin.api.common.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 人员角色关联表
 * </p>
 *
 * @author lim
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "UserRoleDTO对象", description = "人员角色关联表")
public class UserRoleDTO extends BaseDTO {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;
}
