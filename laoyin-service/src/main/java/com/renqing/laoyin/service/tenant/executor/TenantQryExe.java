package com.bat.laoyin.service.tenant.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.tenant.dto.TenantQry;
import com.bat.laoyin.dao.tenant.TenantMapper;
import com.bat.laoyin.dao.tenant.dataobject.TenantDO;
import com.bat.laoyin.service.common.tenant.convertor.TenantConvertor;
import com.bat.laoyin.service.common.utils.SqlUtils;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class TenantQryExe extends ServiceQryImpl<TenantMapper, TenantDO> {
    @Resource
    private TenantMapper mapper;

    public IPage<TenantDO> selectPageVo(IPage<TenantDO> page, TenantQry qry) {
        TenantDO aDo = TenantConvertor.toTenantDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<TenantDO> selectVo(TenantQry qry) {
        TenantDO aDo = TenantConvertor.toTenantDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
