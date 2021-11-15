package com.bat.laoyin.api.makeup;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.makeup.dto.MakeupSettingProductCmd;
import com.bat.laoyin.api.makeup.dto.MakeupSettingProductQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupSettingProductDTO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingProductDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
public interface MakeupSettingProductService
    extends IServiceCmd<MakeupSettingProductDO>, IServiceQry<MakeupSettingProductDO> {

    IPage<MakeupSettingProductDTO> selectPageVo(IPage<MakeupSettingProductDO> page, MakeupSettingProductQry qry);

    List<MakeupSettingProductDO> selectVo(MakeupSettingProductQry qry);

    void mySave(MakeupSettingProductCmd aDo);
}
