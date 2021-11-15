package com.bat.laoyin.api.makeup.dto;

import java.util.List;

import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDeadlineDO;

import lombok.Data;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/16 16:50
 */
@Data
public class MakeupSettingCmd {
    private MakeupSettingDO makeupSetting;
    private List<MakeupSettingDeadlineDO> settingDeadlines;
}
