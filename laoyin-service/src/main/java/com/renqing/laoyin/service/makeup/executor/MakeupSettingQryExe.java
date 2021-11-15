package com.bat.laoyin.service.makeup.executor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.makeup.dto.MakeupSettingQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupSettingDeadlineDTO;
import com.bat.laoyin.api.makeup.dto.data.MakeupSettingRespDTO;
import com.bat.laoyin.dao.makeup.MakeupSettingDeadlineMapper;
import com.bat.laoyin.dao.makeup.MakeupSettingMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDeadlineDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.makeup.convertor.MakeupSettingConvertor;
import com.bat.laoyin.service.makeup.convertor.MakeupSettingDeadlineConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class MakeupSettingQryExe extends ServiceQryImpl<MakeupSettingMapper, MakeupSettingDO> {
    @Resource
    private MakeupSettingMapper mapper;

    @Resource
    private MakeupSettingDeadlineMapper settingDeadlineMapper;

    public IPage<MakeupSettingDO> selectPageVo(IPage<MakeupSettingDO> page, MakeupSettingQry qry) {
        MakeupSettingDO aDo = MakeupSettingConvertor.toMakeupSettingDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        Map<String, Object> map = new HashMap<>(16);
        map.put("createdStartTime", qry.getCreatedStartTime());
        map.put("createdEndTime", qry.getCreatedEndTime());
        return mapper.selectPageVo(page, aDo, sort, map);
    }

    public List<MakeupSettingDO> selectVo(MakeupSettingQry qry) {
        MakeupSettingDO aDo = MakeupSettingConvertor.toMakeupSettingDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }

    public List<MakeupSettingRespDTO> myListByIds(List<Integer> ids) {
        List<MakeupSettingDO> makeupSetting = mapper.selectBatchIds(ids);
        return makeupSetting.stream().map(makeupSettingDO -> {
            MakeupSettingRespDTO dto = new MakeupSettingRespDTO();
            dto.setMakeupSetting(MakeupSettingConvertor.toMakeupSettingDTO(makeupSettingDO));
            List<MakeupSettingDeadlineDO> settingDeadline =
                settingDeadlineMapper.listByMakeupSettingId(makeupSettingDO.getId());
            List<MakeupSettingDeadlineDTO> makeupSettingDeadline =
                MakeupSettingDeadlineConvertor.toMakeupSettingDeadlineDTOList(settingDeadline);
            dto.setSettingDeadlines(makeupSettingDeadline);
            return dto;
        }).collect(Collectors.toList());
    }
}
