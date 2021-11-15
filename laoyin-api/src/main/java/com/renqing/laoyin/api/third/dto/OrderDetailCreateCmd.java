package com.bat.laoyin.api.third.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "OrderDetailCreateCmd对象", description = "订单明细表")
public class OrderDetailCreateCmd {

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

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true, hidden = true)
    private Short status;

    @ApiModelProperty(value = "订单id", required = false, hidden = true)
    private Integer orderId;

    @ApiModelProperty(value = "商品id", required = true, hidden = true)
    private Integer goodsId;

    @ApiModelProperty(value = "第三方商品数量", required = true, example = "1")
    private Integer itemCount;

    @ApiModelProperty(value = "文件路径（生产图）", required = true,
        example = "")
    private String filePath;

    @ApiModelProperty(value = "效果文件路径（效果图）", required = true,
        example = "")
    private String effectFilePath;

    @ApiModelProperty(value = "标签文件路径（标签图）", required = true,
        example = "")
    private String labelFilePath;

    /**
     * 以下为商品信息
     */
    @ApiModelProperty(value = "产品code（商品:产品 n:1）生产系统中的产品编码，用于匹配，传空则代表需要人工匹配")
    private String productCode;

    @ApiModelProperty(value = "sku", hidden = true)
    private String sku;

    @ApiModelProperty(value = "第三方商品名称（参与sku唯一校验）", required = true, example = "定制玻璃手机壳")
    private String name;

    @ApiModelProperty(value = "第三方商品编码（参与sku唯一校验）", required = true, example = "G1727E4B1E05")
    private String code;

    @ApiModelProperty(value = "第三方商品规格（参与sku唯一校验）", required = true, example = "iPhone 7/8")
    private String spec;

    @ApiModelProperty(value = "第三方商品单位", required = true, example = "个")
    private String unitName;

}
