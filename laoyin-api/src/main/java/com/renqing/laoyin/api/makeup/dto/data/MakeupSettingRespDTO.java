package com.bat.laoyin.api.makeup.dto.data;

import java.util.List;

import lombok.Data;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/22 11:37
 */
@Data
public class MakeupSettingRespDTO {
    private MakeupSettingDTO makeupSetting;
    private List<MakeupSettingDeadlineDTO> settingDeadlines;
}
