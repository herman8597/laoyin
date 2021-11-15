package com.bat.laoyin.service.common.region;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.region.RegionService;
import com.bat.laoyin.api.region.dto.RegionQry;
import com.bat.laoyin.dao.region.RegionMapper;
import com.bat.laoyin.dao.region.dataobject.RegionDO;
import com.bat.laoyin.service.common.region.executor.RegionCmdExe;
import com.bat.laoyin.service.common.region.executor.RegionQryExe;

/**
 * <p>
 * 省市区表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-17
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, RegionDO> implements RegionService {

    @Resource
    private RegionCmdExe cmdExe;

    @Resource
    private RegionQryExe qryExe;

    public RegionServiceImpl(@Autowired RegionCmdExe serviceCmd, @Autowired RegionQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<RegionDO> selectPageVo(IPage<RegionDO> page, RegionQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<RegionDO> selectVo(RegionQry qry) {
        return qryExe.selectVo(qry);
    }

}