package com.bat.laoyin.api.makeup.dto;

import com.bat.laoyin.api.common.BasePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 拼版任务与物料关联表
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "MakeupTaskMaterialDTO对象", description = "拼版任务与物料关联表")
public class MakeupTaskMaterialQry extends BasePage {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "拼版任务id")
    private Integer makeupTaskId;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "订单编码")
    private String orderNo;

    @ApiModelProperty(value = "订单明细id")
    private Integer orderDetailId;

    @ApiModelProperty(value = "配货码")
    private String distributionCode;

    @ApiModelProperty(value = "配货码索引(-1-2-3)")
    private Integer distributionCodeIndex;

    @ApiModelProperty(value = "当前物料在某个订单中的索引")
    private Integer materialIndex;

    @ApiModelProperty(value = "某个订单的物料总数")
    private Integer materialSum;

    @ApiModelProperty(value = "物料的排序")
    private Integer materialSort;
}
