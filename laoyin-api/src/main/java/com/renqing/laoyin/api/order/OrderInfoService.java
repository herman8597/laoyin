package com.bat.laoyin.api.order;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.order.dto.OrderInfoQry;
import com.bat.laoyin.api.order.dto.data.OrderInfoDTO;
import com.bat.laoyin.api.third.dto.ThirdOrderCreateCmd;
import com.bat.laoyin.dao.order.dataobject.OrderInfoDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
public interface OrderInfoService extends IServiceCmd<OrderInfoDO>, IServiceQry<OrderInfoDO> {

    IPage<OrderInfoDTO> selectPageVo(IPage<OrderInfoDO> page, OrderInfoQry qry);

    List<OrderInfoDO> selectVo(OrderInfoQry qry);

    /**
     * 第三方订单创建
     * 
     * @param cmd
     */
    void mySave(ThirdOrderCreateCmd cmd);

    List<OrderInfoDTO> myListByIds(List<Integer> ids);

    /**
     * 审核增量更新
     * 
     * @param newObj
     */
    void myUpdatePutById(OrderInfoDTO newObj);
}
