package com.bat.laoyin.api.makeup.dto.data;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lim
 * @date 2021/9/27 21:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "DistributionCodeDTO对象", description = "配货码标签封装类")
public class DistributionCodeRespDTO extends PdfFileBaseRespDTO {
    @ApiModelProperty(value = "配货标签编码")
    private String distributionCode;
    private List<DistributionCodeItemRespDTO> distributionCodeItems;
}
