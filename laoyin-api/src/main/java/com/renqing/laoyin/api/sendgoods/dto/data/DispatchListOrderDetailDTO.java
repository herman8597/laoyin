package com.bat.laoyin.api.sendgoods.dto.data;

import com.bat.laoyin.api.common.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Accessors(chain = true)
@ApiModel(value = "DispatchListOrderDetailDTO对象", description = "包装配送行项表单")
public class DispatchListOrderDetailDTO extends BaseDTO {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "发货单id")
    private Integer dispatchListId;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "订单明细id")
    private Integer orderDetailId;

    @ApiModelProperty(value = "配货码")
    private String distributionCode;

    @ApiModelProperty(value = "第三方订单号(扩展)")
    private String thirdPartyNo;

    @ApiModelProperty(value = "产品编码(扩展)")
    private String productCode;

    @ApiModelProperty(value = "产品名称(扩展)")
    private String productName;

    @ApiModelProperty(value = "产品规格名称(扩展)")
    private String productSpecName;

    @ApiModelProperty(value = "产品品牌名称(扩展)")
    private String productBrandName;

    @ApiModelProperty(value = "产品品牌名称(扩展)")
    private String unitName;

    @ApiModelProperty(value = "商品数量(扩展)")
    private Integer itemCount;
}
