package com.bat.laoyin.service.makeup.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.makeup.dto.MakeupTaskProductQry;
import com.bat.laoyin.dao.makeup.MakeupTaskProductMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskProductDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.makeup.convertor.MakeupTaskProductConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class MakeupTaskProductQryExe extends ServiceQryImpl<MakeupTaskProductMapper, MakeupTaskProductDO> {
    @Resource
    private MakeupTaskProductMapper mapper;

    public IPage<MakeupTaskProductDO> selectPageVo(IPage<MakeupTaskProductDO> page, MakeupTaskProductQry qry) {
        MakeupTaskProductDO aDo = MakeupTaskProductConvertor.toMakeupTaskProductDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<MakeupTaskProductDO> selectVo(MakeupTaskProductQry qry) {
        MakeupTaskProductDO aDo = MakeupTaskProductConvertor.toMakeupTaskProductDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
