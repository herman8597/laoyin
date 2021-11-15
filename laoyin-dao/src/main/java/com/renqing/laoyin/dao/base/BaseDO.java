package com.bat.laoyin.dao.base;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 11:44
 */
@Data
public class BaseDO {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** id */
    @ApiModelProperty(value = "主键Id", required = false)
    @TableId(type = IdType.AUTO)
    protected Integer id;
    /** 创建人 */
    @ApiModelProperty(value = "创建人", required = false, example = "1", hidden = true)
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    protected Integer createdBy;
    @ApiModelProperty(value = "创建人名称", required = false, example = "1", hidden = true)
    @TableField(value = "created_name", exist = false)
    protected String createdName;
    /** 更新人 */
    @ApiModelProperty(value = "更新人", required = false, example = "1", hidden = true)
    @TableField(value = "updated_by", fill = FieldFill.INSERT_UPDATE)
    protected Integer updatedBy;
    @ApiModelProperty(value = "更新人名称", required = false, example = "1", hidden = true)
    @TableField(value = "updated_name", exist = false)
    protected String updatedName;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", dataType = "date", example = "2021-08-06 00:00:00", required = false,
        hidden = true)
    @JsonFormat(pattern = DATE_FORMAT)
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    protected Date createdAt;
    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", dataType = "date", example = "2021-08-06 00:00:00", required = false,
        hidden = true)
    @JsonFormat(pattern = DATE_FORMAT)
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    protected Date updatedAt;
}
