package com.bat.laoyin.dao.sendgoods.dataobject;

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
 * 发货平台表
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("send_goods_platform")
@ApiModel(value = "SendGoodsPlatformDO对象", description = "发货平台表")
public class SendGoodsPlatformDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "平台编码", required = true)
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "平台名称", required = true)
    @TableField("name")
    private String name;

}
