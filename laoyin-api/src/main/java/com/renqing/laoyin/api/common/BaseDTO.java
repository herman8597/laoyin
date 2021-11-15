package com.bat.laoyin.api.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: lim
 * @description: 阿里表设计规范 所有表共有的字段
 * @date: 2021/8/4 21:59
 */
@Data
@ApiModel(value = "BaseDTO", description = "DTO基类")
public class BaseDTO {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** id */
    @ApiModelProperty(value = "主键", required = false)
    protected Integer id;
    /** 创建人 */
    @ApiModelProperty(value = "创建人", required = false)
    protected Integer createdBy;
    @ApiModelProperty(value = "创建人名称", required = false, example = "1")
    protected String createdName;
    /** 更新人 */
    @ApiModelProperty(value = "更新人", required = false)
    protected Integer updatedBy;
    @ApiModelProperty(value = "更新人名称", required = false, example = "1")
    protected String updatedName;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", required = false)
    @JsonFormat(pattern = DATE_FORMAT)
    protected Date createdAt;
    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", required = false)
    @JsonFormat(pattern = DATE_FORMAT)
    protected Date updatedAt;

}
