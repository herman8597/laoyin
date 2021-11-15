package com.bat.laoyin.api.makeup.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/16 20:13
 */
@Data
public class MakeupSettingProductCmd {
    @ApiModelProperty(value = "拼版设置id")
    private Integer makeupSettingId;
    @ApiModelProperty(value = "产品id集合")
    private List<Integer> productIds;
}
