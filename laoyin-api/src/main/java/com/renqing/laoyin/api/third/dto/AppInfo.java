package com.bat.laoyin.api.third.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/9 18:31
 */
@Data
@ApiModel(value = "AppInfo对象", description = "用户鉴权信息")
public class AppInfo {
    @ApiModelProperty(value = "appKey", required = true, example = "rq0KlOoTV7")
    private String appKey;
    @ApiModelProperty(value = "appSecret", required = true, example = "ab672910efc6d69209438c2537662cfff7bebf7b")
    private String appSecret;
}
