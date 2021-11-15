package com.bat.laoyin.api.makeup.dto.data;

import com.bat.laoyin.api.common.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Accessors(chain = true)
@ApiModel(value = "MakeupSettingDTO对象", description = "拼版设置表")
public class MakeupSettingDTO extends BaseDTO {

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "逻辑删除标志位 0正常 1删除")
    private Short deleteFlag;

    @ApiModelProperty(value = "行数据状态 0禁用 1启用")
    private Short status;

    @ApiModelProperty(value = "拼版名称")
    private String name;

    @ApiModelProperty(value = "拼版类型 1手机壳6拼 2手机壳8拼 3手机壳132模")
    private Short type;

    @ApiModelProperty(value = "拼版文件保存路径")
    private String filePath;

    @ApiModelProperty(value = "拼版图片个数")
    private Integer maxCount;

    @ApiModelProperty(value = "拼版尺寸宽(mm)")
    private Integer width;

    @ApiModelProperty(value = "拼版尺寸高(mm)")
    private Integer height;

}
