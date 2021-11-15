package com.bat.laoyin.api.makeup;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.makeup.dto.MakeupTaskMaterialQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupTaskMaterialDTO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskMaterialDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
public interface MakeupTaskMaterialService
    extends IServiceCmd<MakeupTaskMaterialDO>, IServiceQry<MakeupTaskMaterialDO> {

    IPage<MakeupTaskMaterialDTO> selectPageVo(IPage<MakeupTaskMaterialDO> page, MakeupTaskMaterialQry qry);

    List<MakeupTaskMaterialDO> selectVo(MakeupTaskMaterialQry qry);

    /**
     * 获取 拼版任务 订单号列表 不分页
     * 
     * @param qry
     * @return
     */
    List<MakeupTaskMaterialDTO> listOrderMaterial(MakeupTaskMaterialQry qry);
}
