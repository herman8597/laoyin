package com.bat.laoyin.dao.user.dataobject;

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
 * 人员角色关联表
 * </p>
 *
 * @author lim
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("user_role")
@ApiModel(value = "UserRoleDO对象", description = "人员角色关联表")
public class UserRoleDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "用户id", required = true)
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "角色id", required = true)
    @TableField("role_id")
    private Integer roleId;

}
