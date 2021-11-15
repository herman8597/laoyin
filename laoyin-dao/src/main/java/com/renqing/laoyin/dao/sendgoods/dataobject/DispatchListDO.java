package com.bat.laoyin.dao.sendgoods.dataobject;

import java.util.Date;

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
 * 发货单表
 * </p>
 *
 * @author lim
 * @since 2021-10-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("dispatch_list")
@ApiModel(value = "DispatchListDO对象", description = "发货单表")
public class DispatchListDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = true)
    @TableField("tenant_id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0无效占位 1未发货 2已发货", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "发货单编码", required = true)
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "配送方式id", required = true)
    @TableField("distribution_mode_id")
    private Integer distributionModeId;

    @ApiModelProperty(value = "发货总数", required = true)
    @TableField("product_sum")
    private Integer productSum;

    @ApiModelProperty(value = "扫描总数", required = true)
    @TableField("scan_sum")
    private Integer scanSum;

    @ApiModelProperty(value = "物流单号")
    @TableField("logistics_code")
    private String logisticsCode;

    @ApiModelProperty(value = "发货信息id", required = true)
    @TableField("order_deliver_id")
    private Integer orderDeliverId;

    @ApiModelProperty(value = "收货人", required = true)
    @TableField("consignee")
    private String consignee;

    @ApiModelProperty(value = "手机号", required = true)
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "国家id")
    @TableField("country_id")
    private Integer countryId;

    @ApiModelProperty(value = "国家名称")
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

    @ApiModelProperty(value = "发货时间", required = false)
    @TableField("delivery_time")
    private Date deliveryTime;

}
