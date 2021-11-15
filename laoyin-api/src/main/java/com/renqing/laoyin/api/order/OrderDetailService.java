package com.bat.laoyin.api.order;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.order.dto.OrderDetailQry;
import com.bat.laoyin.dao.order.dataobject.OrderDetailDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
public interface OrderDetailService extends IServiceCmd<OrderDetailDO>, IServiceQry<OrderDetailDO> {

    IPage<OrderDetailDO> selectPageVo(IPage<OrderDetailDO> page, OrderDetailQry qry);

    List<OrderDetailDO> selectVo(OrderDetailQry qry);

}
