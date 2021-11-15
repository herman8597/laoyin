package com.bat.laoyin.api.sendgoods.dto.data;

import com.bat.laoyin.api.common.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 发货配送方式
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SendGoodsDistributionModeDTO对象", description = "发货配送方式")
public class SendGoodsDistributionModeDTO extends BaseDTO {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "配送/快递方式名称")
    private String logisticsName;

    @ApiModelProperty(value = "配送/快递方式编码")
    private String logisticsCode;

    @ApiModelProperty(value = "配送类型编码")
    private String distributionTypeCode;

    @ApiModelProperty(value = "配送类型名称")
    private String distributionTypeName;

    @ApiModelProperty(value = "发货地址id")
    private Integer addressId;

    @ApiModelProperty(value = "发货地址名称")
    private String addressName;

    @ApiModelProperty(value = "发货平台id（多选逗号分隔）")
    private Integer platformId;

    @ApiModelProperty(value = "发货平台名称（多选逗号分隔）")
    private String platformName;

    @ApiModelProperty(value = "省的id集合")
    private String provinceIds;

    @ApiModelProperty(value = "市的id集合")
    private String cityIds;

    @ApiModelProperty(value = "编码")
    private String customerName;

    @ApiModelProperty(value = "秘钥")
    private String customerPwd;

    @ApiModelProperty(value = "月结号")
    private String monthCode;

    @ApiModelProperty(value = "网点编码")
    private String sendSite;

    @ApiModelProperty(value = "收件快递员")
    private String sendStaff;

    @ApiModelProperty(value = "邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付(仅SF支持)")
    private Integer payType;
}
