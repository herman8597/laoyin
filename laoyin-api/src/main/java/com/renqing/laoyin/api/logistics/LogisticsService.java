package com.bat.laoyin.api.logistics;

import com.bat.laoyin.api.logistics.dto.KDNOrderOnlineReq;
import com.bat.laoyin.api.logistics.dto.KDNOrderOnlineResp;

/**
 * @author: lim
 * @description: 物流服务
 * @date: 2021/10/12 9:26
 */
public interface LogisticsService {
    /**
     * 电子面单 在线下单
     * 
     * @return
     */
    KDNOrderOnlineResp orderOnline(KDNOrderOnlineReq kdnOrderOnlineReq);
}
