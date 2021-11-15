package com.bat.laoyin.service.common.region.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.region.dto.RegionQry;
import com.bat.laoyin.dao.region.RegionMapper;
import com.bat.laoyin.dao.region.dataobject.RegionDO;
import com.bat.laoyin.service.common.region.convertor.RegionConvertor;
import com.bat.laoyin.service.common.utils.SqlUtils;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class RegionQryExe extends ServiceQryImpl<RegionMapper, RegionDO> {
    @Resource
    private RegionMapper mapper;

    public IPage<RegionDO> selectPageVo(IPage<RegionDO> page, RegionQry qry) {
        RegionDO aDo = RegionConvertor.toRegionDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<RegionDO> selectVo(RegionQry qry) {
        RegionDO aDo = RegionConvertor.toRegionDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
