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
 * 订单明细表
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("order_detail")
@ApiModel(value = "OrderDetailDO对象", description = "订单明细表")
public class OrderDetailDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = false, hidden = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "订单id", required = false)
    @TableField("order_id")
    private Integer orderId;

    @ApiModelProperty(value = "订单编号", required = false, hidden = true)
    @TableField(value = "order_no", exist = false)
    private String orderNo;

    @ApiModelProperty(value = "商品id", required = true)
    @TableField("goods_id")
    private Integer goodsId;

    @ApiModelProperty(value = "商品数量", required = true)
    @TableField("item_count")
    private Integer itemCount;

    @ApiModelProperty(value = "文件路径（生产图）", required = true)
    @TableField("file_path")
    private String filePath;

    @ApiModelProperty(value = "效果文件路径（效果图）", required = true)
    @TableField("effect_file_path")
    private String effectFilePath;

    @ApiModelProperty(value = "标签文件路径（标签图）", required = true)
    @TableField("label_file_path")
    private String labelFilePath;

    @ApiModelProperty(value = "发货平台编码（参与sku唯一校验）")
    @TableField("platform_code")
    private String platformCode;

    @ApiModelProperty(value = "配送方式id")
    @TableField("distribution_mode_id")
    private Integer distributionModeId;

    @ApiModelProperty(value = "产品编码")
    @TableField("product_code")
    private String productCode;

    @ApiModelProperty(value = "产品名称")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty(value = "产品规格名称")
    @TableField("product_spec_name")
    private String productSpecName;

    @ApiModelProperty(value = "产品品牌名称")
    @TableField("product_brand_name")
    private String productBrandName;

}
