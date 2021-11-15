package com.bat.laoyin.api.makeup;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.makeup.dto.MakeupSettingDeadlineQry;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDeadlineDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
public interface MakeupSettingDeadlineService
    extends IServiceCmd<MakeupSettingDeadlineDO>, IServiceQry<MakeupSettingDeadlineDO> {

    IPage<MakeupSettingDeadlineDO> selectPageVo(IPage<MakeupSettingDeadlineDO> page, MakeupSettingDeadlineQry qry);

    List<MakeupSettingDeadlineDO> selectVo(MakeupSettingDeadlineQry qry);

    /**
     * 重新校验截稿时间
     */
    void fixDeadlineTime();

}
