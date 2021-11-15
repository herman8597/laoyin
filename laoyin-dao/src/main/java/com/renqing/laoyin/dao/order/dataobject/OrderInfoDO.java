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
 * 订单(基本信息)表
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("order_info")
@ApiModel(value = "OrderInfoDO对象", description = "订单(基本信息)表")
public class OrderInfoDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = false, hidden = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0无效占位 1待审核 2审核中 3待生产 4生产中 5待发货 6已发货 7已收货 8已取消", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "行数据审核状态 0无效占位 1审核 2非审核", required = true, hidden = true)
    @TableField(value = "check_status", exist = false)
    private Short checkStatus;

    @ApiModelProperty(value = "订单号", required = true)
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty(value = "第三方订单号", required = true, example = "10000")
    @TableField("third_party_no")
    private String thirdPartyNo;

    @ApiModelProperty(value = "客户id", required = true)
    @TableField("customer_id")
    private Integer customerId;

    @ApiModelProperty(value = "客户名称", required = true)
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty(value = "订单类型 1 API 导入 2售后订单", required = true, example = "1")
    @TableField("order_type")
    private Short orderType;

    @ApiModelProperty(value = "审核人", required = true, example = "1")
    @TableField("checked_by")
    private Integer checkedBy;

    @ApiModelProperty(value = "下单人(买家)", required = true, example = "齐天大圣")
    @TableField("buyer")
    private String buyer;

    @ApiModelProperty(value = "备注", example = "尽快发货")
    @TableField("remark")
    private String remark;

}
