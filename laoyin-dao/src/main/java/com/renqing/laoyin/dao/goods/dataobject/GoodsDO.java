package com.bat.laoyin.dao.goods.dataobject;

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
 * 商品表
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("goods")
@ApiModel(value = "GoodsDO对象", description = "商品表")
public class GoodsDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = false, hidden = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "客户id", required = true)
    @TableField("customer_id")
    private Integer customerId;

    @ApiModelProperty(value = "客户名称", required = false, hidden = true)
    @TableField(value = "customer_id", exist = false)
    private String customerName;

    @ApiModelProperty(value = "产品code（商品:产品 n:1）")
    @TableField("product_code")
    private String productCode;

    @ApiModelProperty(value = "sku", required = true)
    @TableField("sku")
    private String sku;

    @ApiModelProperty(value = "第三方商品名称（参与sku唯一校验）", required = true)
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "第三方商品编码（参与sku唯一校验）", required = true)
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "第三方商品规格（参与sku唯一校验）", required = true)
    @TableField("spec")
    private String spec;

    @ApiModelProperty(value = "第三方商品单位", required = true)
    @TableField("unit_name")
    private String unitName;

    @ApiModelProperty(value = "发货平台编码（参与sku唯一校验）")
    @TableField("platform_code")
    private String platformCode;

    @ApiModelProperty(value = "配送方式id")
    @TableField("distribution_mode_id")
    private Integer distributionModeId;

    @ApiModelProperty(value = "第三方商品备注")
    @TableField("remark")
    private String remark;

}
