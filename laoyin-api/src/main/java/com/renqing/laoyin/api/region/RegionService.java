package com.bat.laoyin.api.region;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.region.dto.RegionQry;
import com.bat.laoyin.dao.region.dataobject.RegionDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-17
 */
public interface RegionService extends IServiceCmd<RegionDO>, IServiceQry<RegionDO> {

    IPage<RegionDO> selectPageVo(IPage<RegionDO> page, RegionQry qry);

    List<RegionDO> selectVo(RegionQry qry);

}
