package com.bat.laoyin.api.goods.dto;

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
 * 商品表
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "GoodsQry对象", description = "商品表")
public class GoodsQry extends BasePage {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "产品code/编码（商品:产品 n:1）")
    private String productCode;

    @ApiModelProperty(value = "sku/商品编码")
    private String sku;

    @ApiModelProperty(value = "第三方商品名称（参与sku唯一校验）")
    private String name;

    @ApiModelProperty(value = "第三方商品编码（参与sku唯一校验）")
    private String code;

    @ApiModelProperty(value = "第三方商品规格（参与sku唯一校验）")
    private String spec;

    @ApiModelProperty(value = "第三方商品单位")
    private String unitName;

    @ApiModelProperty(value = "发货平台编码（参与sku唯一校验）")
    private String platformCode;

    @ApiModelProperty(value = "配送方式id")
    private Integer distributionModeId;

    @ApiModelProperty(value = "匹配开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date matchStartTime;

    @ApiModelProperty(value = "匹配结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date matchEndTime;

    @ApiModelProperty(value = "第三方商品备注")
    private String remark;
}
