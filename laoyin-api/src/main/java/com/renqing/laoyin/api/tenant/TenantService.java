package com.bat.laoyin.api.tenant;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.tenant.dto.TenantQry;
import com.bat.laoyin.dao.tenant.dataobject.TenantDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-08
 */
public interface TenantService extends IServiceCmd<TenantDO>, IServiceQry<TenantDO> {

    IPage<TenantDO> selectPageVo(IPage<TenantDO> page, TenantQry qry);

    List<TenantDO> selectVo(TenantQry qry);

    /**
     * 自定义更新
     * 
     * @param aDo
     */
    void mySave(TenantDO aDo);
}
