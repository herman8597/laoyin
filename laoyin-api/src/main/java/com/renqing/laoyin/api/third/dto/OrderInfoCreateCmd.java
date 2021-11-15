package com.bat.laoyin.api.third.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "OrderInfoCreateCmd对象", description = "订单(基本信息)表")
public class OrderInfoCreateCmd {

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

    @ApiModelProperty(value = "行数据状态 0无效占位 1待审核 2审核中 3待生产 4生产中 5待发货 6已发货 7已收货 8已取消", required = true, hidden = true)
    private Short status;

    @ApiModelProperty(value = "订单号", required = true, hidden = true)
    private String orderNo;

    @ApiModelProperty(value = "第三方订单号", required = true, example = "40705")
    private String thirdPartyNo;

    @ApiModelProperty(value = "客户id", required = true, hidden = true)
    private Integer customerId;

    @ApiModelProperty(value = "客户名称", required = true, hidden = true)
    private String customerName;

    @ApiModelProperty(value = "订单类型 1 API 导入 2售后订单", required = true, example = "1", hidden = true)
    private Short orderType;

    @ApiModelProperty(value = "审核人", required = true, example = "1", hidden = true)
    private Integer checkedBy;

    @ApiModelProperty(value = "下单人(买家)", required = true, example = "齐天大圣")
    private String buyer;

    @ApiModelProperty(value = "备注", example = "尽快发货")
    private String remark;

}
