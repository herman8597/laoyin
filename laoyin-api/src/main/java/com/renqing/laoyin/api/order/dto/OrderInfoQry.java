package com.bat.laoyin.api.order.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bat.laoyin.api.common.BasePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Accessors(chain = true)
@ApiModel(value = "OrderInfoDTO对象", description = "订单(基本信息)表")
public class OrderInfoQry extends BasePage {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0无效占位 1待审核 2审核中 3待生产 4生产中 5待发货 6已发货 7已收货 8已取消")
    private Short status;

    @ApiModelProperty(value = "行数据审核状态 0无效占位 1审核 2非审核", required = true)
    private Short checkStatus;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "第三方订单号")
    private String thirdPartyNo;

    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "订单类型 1 API 导入 2售后订单")
    private Short orderType;

    @ApiModelProperty(value = "审核人")
    private Integer checkedBy;

    @ApiModelProperty(value = "下单人(买家)")
    private String buyer;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdStartTime;

    @ApiModelProperty(value = "创建时间结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdEndTime;

}
