package com.bat.laoyin.dao.makeup.dataobject;

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
 * 拼版任务与物料关联表
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("makeup_task_material")
@ApiModel(value = "MakeupTaskMaterialDO对象", description = "拼版任务与物料关联表")
public class MakeupTaskMaterialDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "拼版任务id", required = true)
    @TableField("makeup_task_id")
    private Integer makeupTaskId;

    @ApiModelProperty(value = "订单id", required = true)
    @TableField("order_id")
    private Integer orderId;

    @ApiModelProperty(value = "订单编码", required = true)
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty(value = "订单明细id", required = true)
    @TableField("order_detail_id")
    private Integer orderDetailId;

    @ApiModelProperty(value = "配货码", required = false)
    @TableField("distribution_code")
    private String distributionCode;

    @ApiModelProperty(value = "配货码索引(-1-2-3)", required = false)
    @TableField("distribution_code_index")
    private Integer distributionCodeIndex;

    @ApiModelProperty(value = "当前物料在该订单中的索引", hidden = true)
    @TableField("material_index")
    private Integer materialIndex;

    @ApiModelProperty(value = "该订单的物料总数", hidden = true)
    @TableField("material_sum")
    private Integer materialSum;

    @ApiModelProperty(value = "物料的排序", hidden = true)
    @TableField("material_sort")
    private Integer materialSort;

    @ApiModelProperty(value = "扫描标志 0未扫描 1已扫描", hidden = true)
    @TableField("scan_flag")
    private Short scanFlag;
}
