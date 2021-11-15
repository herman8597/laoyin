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
 * 发货地址表
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("send_goods_address")
@ApiModel(value = "SendGoodsAddressDO对象", description = "发货地址表")
public class SendGoodsAddressDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "简称", required = true)
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "省id", required = true)
    @TableField("province_id")
    private Integer provinceId;

    @ApiModelProperty(value = "省名称", required = true)
    @TableField("province_name")
    private String provinceName;

    @ApiModelProperty(value = "市id", required = true)
    @TableField("city_id")
    private Integer cityId;

    @ApiModelProperty(value = "市名称", required = true)
    @TableField("city_name")
    private String cityName;

    @ApiModelProperty(value = "区id")
    @TableField("area_id")
    private Integer areaId;

    @ApiModelProperty(value = "区名称")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty(value = "详细地址", required = true)
    @TableField("detailed_address")
    private String detailedAddress;

    @ApiModelProperty(value = "发件人", required = true)
    @TableField("sender_name")
    private String senderName;

    @ApiModelProperty(value = "发件人手机号", required = true)
    @TableField("sender_mobile")
    private String senderMobile;

}
