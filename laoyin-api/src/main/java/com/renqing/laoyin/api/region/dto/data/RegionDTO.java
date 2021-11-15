package com.bat.laoyin.api.region.dto.data;

import com.bat.laoyin.api.common.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 省市区表
 * </p>
 *
 * @author lim
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "RegionDTO对象", description = "省市区表")
public class RegionDTO extends BaseDTO {

    @ApiModelProperty(value = "地域名")
    private String regionName;

    @ApiModelProperty(value = "父节点id ")
    private Integer parentId;

    @ApiModelProperty(value = "是否还有下一级 1是  0否")
    private Short haveNext;

    @ApiModelProperty(value = "区域英文名称")
    private String regionNameEn;

    @ApiModelProperty(value = "区域级数 国家/省/市/区 /1/2/3")
    private Short level;
}
