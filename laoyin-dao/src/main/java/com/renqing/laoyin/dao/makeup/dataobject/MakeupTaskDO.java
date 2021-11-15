package com.bat.laoyin.dao.makeup.dataobject;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
 * 拼版任务表
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("makeup_task")
@ApiModel(value = "MakeupTaskDO对象", description = "拼版任务表")
public class MakeupTaskDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态(拼版状态) 0无效占位 1批次等待中 2拼版完成 3拼版失败", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "行数据状态(不满足不拼版标志位) 0禁用 1启用", required = true)
    @TableField("deadline_status")
    private Short deadlineStatus;

    @ApiModelProperty(value = "任务编号", required = true)
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "拼版设置id", required = true)
    @TableField("makeup_setting_id")
    private Integer makeupSettingId;

    @ApiModelProperty(value = "拼版设置名称", required = true)
    @TableField("makeup_setting_name")
    private String makeupSettingName;

    @ApiModelProperty(value = "拼版设置文件路径", required = true)
    @TableField("makeup_setting_file_path")
    private String makeupSettingFilePath;

    @ApiModelProperty(value = "产品数量(实际物料数量)", required = true)
    @TableField("product_count")
    private Integer productCount;

    @ApiModelProperty(value = "拼版截稿时间", required = true)
    @TableField("deadline_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadlineTime;

    @ApiModelProperty(value = "拼版文件存放路径", required = true)
    @TableField("file_path")
    private String filePath;

    @ApiModelProperty(value = "拼版图片个数", required = true)
    @TableField("max_count")
    private Integer maxCount;

    @ApiModelProperty(value = "拼版延迟的截止时间", required = true)
    @TableField("delay_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date delayTime;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

}
