package com.bat.laoyin.api.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jason(XCH3931399 @ 163.com)
 * @date 2021/5/24 10:29
 */
@Data
@ApiModel(description = "分页")
public class BasePage extends BaseDTO {
    // @NotNull(message = "P_PAGE_NULL")
    @ApiModelProperty(value = "页码", required = false, example = "1")
    private Integer page = 1;

    // @NotNull(message = "P_PAGE_SIZE_NULL")
    @ApiModelProperty(value = "每页数量", required = false, example = "10")
    private Integer size = 10;

    @ApiModelProperty(value = "排序 (+|-)(column),...", required = false, example = "1")
    private String sort = "+id";
}
