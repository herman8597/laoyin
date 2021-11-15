package com.bat.laoyin.api.sendgoods.dto.data;

import java.util.Date;
import java.util.List;

import com.bat.laoyin.api.common.BaseDTO;

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
public class DispatchListDTO extends BaseDTO {

    // 1 发货单 2 运单
    private Integer fileType = 1;

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

    @ApiModelProperty(value = "配送方式名称(扩展)")
    private String distributionModeName;

    @ApiModelProperty(value = "客户名称(扩展)")
    private String customerName;

    /**
     * 包装配送数据
     */
    private List<DispatchListOrderDetailDTO> dispatchListOrderDetails;

    /**
     * 发货单 订单行项
     */
    private List<DispatchListItemDTO> dispatchListItems;

    /**
     * 当前扫码的配货标签对应的标签纸url
     */
    private String pdfUrl;

}
