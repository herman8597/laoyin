package com.bat.laoyin.api.order.dto.data;

import com.bat.laoyin.api.common.BaseDTO;
import com.bat.laoyin.api.goods.dto.data.GoodsDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Accessors(chain = true)
@ApiModel(value = "OrderDetailDTO对象", description = "订单明细表")
public class OrderDetailDTO extends BaseDTO {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "商品数量")
    private Integer itemCount;

    @ApiModelProperty(value = "文件路径（生产图）")
    private String filePath;

    @ApiModelProperty(value = "效果文件路径（效果图）")
    private String effectFilePath;

    @ApiModelProperty(value = "标签文件路径（标签图）")
    private String labelFilePath;

    @ApiModelProperty(value = "发货平台编码（参与sku唯一校验）")
    private String platformCode;

    @ApiModelProperty(value = "配送方式id")
    private Integer distributionModeId;

    @ApiModelProperty(value = "产品编码")
    private String productCode;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "产品规格名称")
    private String productSpecName;

    @ApiModelProperty(value = "产品品牌名称")
    private String productBrandName;

    private GoodsDTO goods;

}
