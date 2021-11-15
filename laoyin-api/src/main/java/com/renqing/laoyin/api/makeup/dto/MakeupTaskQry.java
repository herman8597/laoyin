package com.bat.laoyin.api.makeup.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.bat.laoyin.api.common.BasePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Accessors(chain = true)
@ApiModel(value = "MakeupTaskDTO对象", description = "拼版任务表")
public class MakeupTaskQry extends BasePage {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态(拼版状态) 0无效占位 1批次等待中 2拼版完成 3拼版失败")
    private Short status;

    @ApiModelProperty(value = "行数据状态(不满足不拼版标志位) 0禁用 1启用")
    private Short deadlineStatus;

    @ApiModelProperty(value = "任务编号")
    private String code;

    @ApiModelProperty(value = "拼版设置id")
    private Integer makeupSettingId;

    @ApiModelProperty(value = "拼版设置名称")
    private String makeupSettingName;

    @ApiModelProperty(value = "拼版设置文件路径")
    private String makeupSettingFilePath;

    @ApiModelProperty(value = "产品数量(实际物料数量)")
    private Integer productCount;

    @ApiModelProperty(value = "拼版截稿时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadlineTime;

    @ApiModelProperty(value = "拼版文件存放路径")
    private String filePath;

    @ApiModelProperty(value = "拼版图片个数")
    private Integer maxCount;

    @ApiModelProperty(value = "拼版延迟的截止时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date delayTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "订单编号")
    private String orderId;

    @ApiModelProperty(value = "拼版时间开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date makeupStartTime;

    @ApiModelProperty(value = "拼版时间结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date makeupEndTime;

}
