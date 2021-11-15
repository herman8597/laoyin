package com.bat.laoyin.api.xxljob.service;

import java.util.List;

import com.bat.laoyin.api.xxljob.api.dto.XxlJobRpcCmd;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/22 15:23
 */
public interface XxlJobService {
    void xxlJobAdd(List<XxlJobRpcCmd> cmds);
}
