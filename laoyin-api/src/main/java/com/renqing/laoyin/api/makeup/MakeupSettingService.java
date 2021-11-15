package com.bat.laoyin.api.makeup;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.makeup.dto.MakeupSettingCmd;
import com.bat.laoyin.api.makeup.dto.MakeupSettingQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupSettingRespDTO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
public interface MakeupSettingService extends IServiceCmd<MakeupSettingDO>, IServiceQry<MakeupSettingDO> {

    IPage<MakeupSettingDO> selectPageVo(IPage<MakeupSettingDO> page, MakeupSettingQry qry);

    List<MakeupSettingDO> selectVo(MakeupSettingQry qry);

    void mySave(MakeupSettingCmd aDo);

    void myUpdateById(MakeupSettingCmd newObj);

    List<MakeupSettingRespDTO> myListByIds(List<Integer> ids);
}
