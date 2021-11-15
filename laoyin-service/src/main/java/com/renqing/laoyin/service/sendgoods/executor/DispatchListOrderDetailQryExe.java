package com.bat.laoyin.service.sendgoods.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.sendgoods.dto.DispatchListOrderDetailQry;
import com.bat.laoyin.dao.sendgoods.DispatchListOrderDetailMapper;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListOrderDetailDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.sendgoods.convertor.DispatchListOrderDetailConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class DispatchListOrderDetailQryExe
    extends ServiceQryImpl<DispatchListOrderDetailMapper, DispatchListOrderDetailDO> {
    @Resource
    private DispatchListOrderDetailMapper mapper;

    public IPage<DispatchListOrderDetailDO> selectPageVo(IPage<DispatchListOrderDetailDO> page,
        DispatchListOrderDetailQry qry) {
        DispatchListOrderDetailDO aDo = DispatchListOrderDetailConvertor.toDispatchListOrderDetailDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<DispatchListOrderDetailDO> selectVo(DispatchListOrderDetailQry qry) {
        DispatchListOrderDetailDO aDo = DispatchListOrderDetailConvertor.toDispatchListOrderDetailDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
