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
 * 拼版设置截止时间关联表
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("makeup_setting_deadline")
@ApiModel(value = "MakeupSettingDeadlineDO对象", description = "拼版设置截止时间关联表")
public class MakeupSettingDeadlineDO extends BaseDO {

    @ApiModelProperty(value = "多租户id", required = false)
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除", required = false, hidden = true)
    @TableField("delete_flag")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态（不满足不拼版标志位） 0禁用 1启用", required = true)
    @TableField("status")
    private Short status;

    @ApiModelProperty(value = "拼版设置id", required = false)
    @TableField("makeup_setting_id")
    private Integer makeupSettingId;

    @ApiModelProperty(value = "截稿时间", required = true)
    @TableField("deadline_time")
    private Date deadlineTime;

    @ApiModelProperty(value = "延迟的时间区间毫秒值", required = true)
    @TableField("delay_millisecond")
    private Integer delayMillisecond;

    @ApiModelProperty(value = "延迟的截止时间", required = false, hidden = true)
    @TableField("delay_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date delayTime;

}
