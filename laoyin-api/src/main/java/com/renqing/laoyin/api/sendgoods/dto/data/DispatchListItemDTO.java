package com.bat.laoyin.api.sendgoods.dto.data;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 发货单表
 * </p>
 *
 * @author lim
 * @since 2021-10-09
 */
@Data
@ApiModel(value = "DispatchListDTO对象", description = "发货单订单行项")
public class DispatchListItemDTO {
    List<DispatchListItemItemDTO> dispatchListItemItems;
    @ApiModelProperty(value = "订单id")
    private Integer orderId;
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    @ApiModelProperty(value = "第三方订单号(扩展)")
    private String thirdPartyNo;
}
