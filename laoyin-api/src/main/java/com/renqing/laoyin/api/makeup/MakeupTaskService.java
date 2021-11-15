package com.bat.laoyin.api.makeup;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.makeup.dto.MakeupTaskQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupTaskDTO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
public interface MakeupTaskService extends IServiceCmd<MakeupTaskDO>, IServiceQry<MakeupTaskDO> {

    IPage<MakeupTaskDTO> selectPageVo(IPage<MakeupTaskDO> page, MakeupTaskQry qry);

    List<MakeupTaskDO> selectVo(MakeupTaskQry qry);

    /**
     * 订单拼版
     * 
     * @param orderId
     */
    void makeupOrder(Integer orderId);

    /**
     * 截稿 翻译不太准确
     * 
     * @param makeupTaskId
     */
    void doDeadLine(Integer makeupTaskId);
}
