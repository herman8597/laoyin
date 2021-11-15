package com.bat.laoyin.dao.order.dataobject;

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
 * 订单发货信息表
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("order_deliver")
@ApiModel(value = "OrderDeliverDO对象", description = "订单发货信息表")
public class OrderDeliverDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = false, hidden = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "订单id", required = false)
    @TableField("order_id")
    private Integer orderId;

    @ApiModelProperty(value = "收货人", required = true)
    @TableField("consignee")
    private String consignee;

    @ApiModelProperty(value = "手机号", required = true)
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "国家id", required = false, example = "37")
    @TableField("country_id")
    private Integer countryId;

    @ApiModelProperty(value = "国家名称", required = false, example = "中国")
    @TableField("country_name")
    private String countryName;

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

    @ApiModelProperty(value = "区id", required = true)
    @TableField("area_id")
    private Integer areaId;

    @ApiModelProperty(value = "区名称", required = true)
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty(value = "详细地址", required = true)
    @TableField("detailed_address")
    private String detailedAddress;

}
