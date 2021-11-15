package com.bat.laoyin.dao.region.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 省市区表
 * </p>
 *
 * @author lim
 * @since 2021-08-17
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("region")
@ApiModel(value = "RegionDO对象", description = "省市区表")
public class RegionDO {

    /** id */
    @ApiModelProperty(value = "主键Id")
    @TableId(type = IdType.AUTO)
    protected Integer id;

    @ApiModelProperty(value = "地域名")
    @TableField("region_name")
    private String regionName;

    @ApiModelProperty(value = "父节点id ")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "是否还有下一级 1是  0否")
    @TableField("have_next")
    private Short haveNext;

    @ApiModelProperty(value = "区域英文名称")
    @TableField("region_name_en")
    private String regionNameEn;

    @ApiModelProperty(value = "区域级数 国家/省/市/区 /1/2/3")
    @TableField("level")
    private Short level;

}
