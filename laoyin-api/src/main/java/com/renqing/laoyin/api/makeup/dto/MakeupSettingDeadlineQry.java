package com.bat.laoyin.api.makeup.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bat.laoyin.api.common.BasePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Accessors(chain = true)
@ApiModel(value = "MakeupSettingDeadlineDTO对象", description = "拼版设置截止时间关联表")
public class MakeupSettingDeadlineQry extends BasePage {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态（不满足不拼版标志位） 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "拼版设置id")
    private Integer makeupSettingId;

    @ApiModelProperty(value = "截稿时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadlineTime;

    @ApiModelProperty(value = "延迟的时间区间毫秒值")
    private Integer delayMillisecond;

    @ApiModelProperty(value = "延迟的截止时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date delayTime;
}
