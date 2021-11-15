package com.bat.laoyin.api.order;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.order.dto.OrderDeliverQry;
import com.bat.laoyin.dao.order.dataobject.OrderDeliverDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
public interface OrderDeliverService extends IServiceCmd<OrderDeliverDO>, IServiceQry<OrderDeliverDO> {

    IPage<OrderDeliverDO> selectPageVo(IPage<OrderDeliverDO> page, OrderDeliverQry qry);

    List<OrderDeliverDO> selectVo(OrderDeliverQry qry);

}
