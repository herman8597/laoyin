package com.bat.laoyin.dao.tenant.dataobject;

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
 * @since 2021-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("tenant")
@ApiModel(value = "TenantDO对象", description = "租户表")
public class TenantDO extends BaseDO {

    @ApiModelProperty(value = "	多租户id", required = true)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "客户简称", required = true)
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "客户公司名")
    @TableField("company")
    private String company;

    @ApiModelProperty(value = "联系人id（租户管理员）", required = true)
    @TableField("contact_id")
    private Integer contactId;

    @ApiModelProperty(value = "联系人姓名（冗余）", required = true)
    @TableField("contact_name")
    private String contactName;

    @ApiModelProperty(value = "联系人电话（冗余）", required = true)
    @TableField("contact_phone")
    private String contactPhone;

    @TableField(value = "contact_password", exist = false)
    private String contactPassword;

}
