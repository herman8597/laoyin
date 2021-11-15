package com.bat.laoyin.dao.makeup.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bat.laoyin.dao.base.BaseDO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 拼版设置表
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("makeup_setting")
@ApiModel(value = "MakeupSettingDO对象", description = "拼版设置表")
public class MakeupSettingDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = false, hidden = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "拼版名称", required = true)
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "拼版类型 1手机壳6拼 2手机壳8拼 3手机壳132模", required = true)
    @TableField("type")
    private Short type;

    @ApiModelProperty(value = "拼版文件保存路径", required = true)
    @TableField("file_path")
    private String filePath;

    @ApiModelProperty(value = "拼版图片个数", required = true)
    @TableField("max_count")
    private Integer maxCount;

    @ApiModelProperty(value = "拼版尺寸宽(mm)", required = true)
    @TableField("width")
    private Integer width;

    @ApiModelProperty(value = "拼版尺寸高(mm)", required = true)
    @TableField("height")
    private Integer height;

}
