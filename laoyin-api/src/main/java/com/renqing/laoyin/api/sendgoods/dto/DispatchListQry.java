package com.bat.laoyin.api.sendgoods.dto;

import java.util.Date;

import com.bat.laoyin.api.common.BasePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Accessors(chain = true)
@ApiModel(value = "DispatchListDTO对象", description = "发货单表")
public class DispatchListQry extends BasePage {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0无效占位 1未发货 2已发货")
    private Short status;

    @ApiModelProperty(value = "发货单编码")
    private String code;

    @ApiModelProperty(value = "配送方式id")
    private Integer distributionModeId;

    @ApiModelProperty(value = "发货总数")
    private Integer productSum;

    @ApiModelProperty(value = "扫描总数")
    private Integer scanSum;

    @ApiModelProperty(value = "物流单号")
    private String logisticsCode;

    @ApiModelProperty(value = "发货信息id")
    private Integer orderDeliverId;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "国家id")
    private Integer countryId;

    @ApiModelProperty(value = "国家名称")
    private String countryName;

    @ApiModelProperty(value = "省id")
    private Integer provinceId;

    @ApiModelProperty(value = "省名称")
    private String provinceName;

    @ApiModelProperty(value = "市id")
    private Integer cityId;

    @ApiModelProperty(value = "市名称")
    private String cityName;

    @ApiModelProperty(value = "区id")
    private Integer areaId;

    @ApiModelProperty(value = "区名称")
    private String areaName;

    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "发货时间开始")
    private Date deliveryStartTime;

    @ApiModelProperty(value = "发货时间结束")
    private Date deliveryEndTime;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "配货号")
    private String distributionCode;
}
