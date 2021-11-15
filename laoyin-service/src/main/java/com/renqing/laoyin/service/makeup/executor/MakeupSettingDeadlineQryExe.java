package com.bat.laoyin.service.makeup.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.makeup.dto.MakeupSettingDeadlineQry;
import com.bat.laoyin.dao.makeup.MakeupSettingDeadlineMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDeadlineDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.makeup.convertor.MakeupSettingDeadlineConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class MakeupSettingDeadlineQryExe extends ServiceQryImpl<MakeupSettingDeadlineMapper, MakeupSettingDeadlineDO> {
    @Resource
    private MakeupSettingDeadlineMapper mapper;

    public IPage<MakeupSettingDeadlineDO> selectPageVo(IPage<MakeupSettingDeadlineDO> page,
        MakeupSettingDeadlineQry qry) {
        MakeupSettingDeadlineDO aDo = MakeupSettingDeadlineConvertor.toMakeupSettingDeadlineDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<MakeupSettingDeadlineDO> selectVo(MakeupSettingDeadlineQry qry) {
        MakeupSettingDeadlineDO aDo = MakeupSettingDeadlineConvertor.toMakeupSettingDeadlineDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
