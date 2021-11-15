package com.bat.laoyin.service.common.role.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.role.dto.RoleQry;
import com.bat.laoyin.dao.role.RoleMapper;
import com.bat.laoyin.dao.role.dataobject.RoleDO;
import com.bat.laoyin.service.common.role.convertor.RoleConvertor;
import com.bat.laoyin.service.common.utils.SqlUtils;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class RoleQryExe extends ServiceQryImpl<RoleMapper, RoleDO> {
    @Resource
    private RoleMapper mapper;

    public IPage<RoleDO> selectPageVo(IPage<RoleDO> page, RoleQry qry) {
        RoleDO aDo = RoleConvertor.toRoleDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<RoleDO> selectVo(RoleQry qry) {
        RoleDO aDo = RoleConvertor.toRoleDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
