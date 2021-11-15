package com.bat.laoyin.dao.sendgoods.dataobject;

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
 * 发货单与订单明细关联表
 * </p>
 *
 * @author lim
 * @since 2021-10-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("dispatch_list_order_detail")
@ApiModel(value = "DispatchListOrderDetailDO对象", description = "发货单与订单明细关联表")
public class DispatchListOrderDetailDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = true)
    @TableField("tenant_id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "发货单id", required = true)
    @TableField("dispatch_list_id")
    private Integer dispatchListId;

    @ApiModelProperty(value = "订单id", required = true)
    @TableField("order_id")
    private Integer orderId;

    @ApiModelProperty(value = "订单编号", required = true)
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty(value = "订单明细id", required = true)
    @TableField("order_detail_id")
    private Integer orderDetailId;

    @ApiModelProperty(value = "配货号", required = true)
    @TableField("distribution_code")
    private String distributionCode;

}
