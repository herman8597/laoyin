package com.bat.laoyin.dao.user.dataobject;

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
 * 租户表
 * </p>
 *
 * @author lim
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString(callSuper = true)
@TableName("user")
@ApiModel(value = "UserDO对象", description = "租户表")
public class UserDO extends BaseDO {

    @ApiModelProperty(value = "	多租户id", required = true)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "租户名", required = true)
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "真实租户名", required = true)
    @TableField("real_name")
    private String realName;

    @ApiModelProperty(value = "密码", required = true)
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "手机号", required = true)
    @TableField("phone")
    private String phone;

    @TableField(value = "role_ids", exist = false)
    private List<Integer> roleIds;

}
