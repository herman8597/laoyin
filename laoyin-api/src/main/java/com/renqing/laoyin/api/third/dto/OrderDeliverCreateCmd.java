package com.bat.laoyin.api.third.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "OrderDeliverCreateCmd对象", description = "订单发货信息表")
public class OrderDeliverCreateCmd {

    /** id */
    @ApiModelProperty(value = "主键Id", required = false, hidden = true)
    protected Integer id;
    /** 创建人 */
    @ApiModelProperty(value = "创建人", required = false, example = "1", hidden = true)
    protected Integer createdBy;
    /** 更新人 */
    @ApiModelProperty(value = "更新人", required = false, example = "1", hidden = true)
    protected Integer updatedBy;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", dataType = "date", example = "2021-08-06 00:00:00", required = false,
        hidden = true)
    protected Date createdAt;
    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", dataType = "date", example = "2021-08-06 00:00:00", required = false,
        hidden = true)
    protected Date updatedAt;

    @ApiModelProperty(value = "多租户id", required = true, hidden = true)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = false, hidden = true)
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true, hidden = true)
    private Short status;

    @ApiModelProperty(value = "订单id", required = false, hidden = true)
    private Integer orderId;

    @ApiModelProperty(value = "收货人", required = true, example = "许水娣")
    private String consignee;

    @ApiModelProperty(value = "手机号", required = true, example = "18218752727")
    private String mobile;

    @ApiModelProperty(value = "国家id", required = false, example = "37")
    private Integer countryId = 37;

    @ApiModelProperty(value = "国家名称", required = false, example = "中国")
    private String countryName = "中国";

    @ApiModelProperty(value = "省id", required = false, example = "440000")
    private Integer provinceId;

    @ApiModelProperty(value = "省名称", required = true, example = "广东省")
    private String provinceName;

    @ApiModelProperty(value = "市id", required = false, example = "440300")
    private Integer cityId;

    @ApiModelProperty(value = "市名称", required = true, example = "深圳市")
    private String cityName;

    @ApiModelProperty(value = "区id", required = false, example = "440307")
    private Integer areaId;

    @ApiModelProperty(value = "区名称", required = true, example = "龙岗区")
    private String areaName;

    @ApiModelProperty(value = "详细地址", required = true, example = "坂田街道江灏商务中心12楼")
    private String detailedAddress;

    @ApiModelProperty(value = "发货平台编码（参与sku唯一校验）生产系统中的发货平台编码，用于确定配送方式，传空则代表默认")
    private String platformCode;

    @ApiModelProperty(value = "配送方式id 生产系统中的配送方式id，用于确定配送方式，传空则代表需要人工匹配", hidden = true)
    private Integer distributionModeId;

}
