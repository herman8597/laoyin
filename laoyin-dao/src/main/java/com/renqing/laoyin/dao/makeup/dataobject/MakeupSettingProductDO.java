package com.bat.laoyin.dao.makeup.dataobject;

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
 * 拼版设置与产品关联表
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("makeup_setting_product")
@ApiModel(value = "MakeupSettingProductDO对象", description = "拼版设置与产品关联表")
public class MakeupSettingProductDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "拼版设置id", required = true)
    @TableField("makeup_setting_id")
    private Integer makeupSettingId;

    @ApiModelProperty(value = "产品id", required = true)
    @TableField("product_id")
    private Integer productId;

}
