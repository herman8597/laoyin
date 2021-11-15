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
 * 发货配送方式
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("send_goods_distribution_mode")
@ApiModel(value = "SendGoodsDistributionModeDO对象", description = "发货配送方式")
public class SendGoodsDistributionModeDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "配送/快递方式名称", required = true)
    @TableField("logistics_name")
    private String logisticsName;

    @ApiModelProperty(value = "配送/快递方式编码", required = true)
    @TableField("logistics_code")
    private String logisticsCode;

    @ApiModelProperty(value = "配送类型编码", required = true)
    @TableField("distribution_type_code")
    private String distributionTypeCode;

    @ApiModelProperty(value = "配送类型名称", required = true)
    @TableField("distribution_type_name")
    private String distributionTypeName;

    @ApiModelProperty(value = "发货地址id", required = true)
    @TableField("address_id")
    private Integer addressId;

    @ApiModelProperty(value = "发货地址名称", required = true)
    @TableField("address_name")
    private String addressName;

    @ApiModelProperty(value = "发货平台id（多选逗号分隔）", required = true)
    @TableField("platform_code")
    private String platformCode;

    @ApiModelProperty(value = "发货平台名称（多选逗号分隔）", required = true)
    @TableField(value = "platform_name", exist = false)
    private String platformName;

    @ApiModelProperty(value = "省的id集合", required = true)
    @TableField("province_ids")
    private String provinceIds;

    @ApiModelProperty(value = "市的id集合", required = true)
    @TableField("city_ids")
    private String cityIds;

    @ApiModelProperty(value = "编码")
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty(value = "秘钥")
    @TableField("customer_pwd")
    private String customerPwd;

    @ApiModelProperty(value = "月结号")
    @TableField("month_code")
    private String monthCode;

    @ApiModelProperty(value = "网点编码")
    @TableField("send_site")
    private String sendSite;

    @ApiModelProperty(value = "收件快递员")
    @TableField("send_staff")
    private String sendStaff;

    @ApiModelProperty(value = "邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付(仅SF支持)")
    @TableField("pay_type")
    private Integer payType;

}
