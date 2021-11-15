package com.bat.laoyin.api.print;

import java.util.concurrent.Future;

import com.bat.laoyin.api.logistics.dto.KDNOrderOnlineResp;
import com.bat.laoyin.api.makeup.dto.data.DispatchListRespDTO;
import com.bat.laoyin.api.makeup.dto.data.DistributionCodeRespDTO;
import com.bat.laoyin.api.makeup.dto.data.MaterialRequisitionRespDTO;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/26 19:30
 */
public interface PrintService {
    /**
     * 获取领料单
     *
     * @param code
     * @return
     */
    Future<MaterialRequisitionRespDTO> getMaterialRequisition(String code);

    /**
     * 获取配货标签
     *
     * @param code
     * @return
     */
    Future<DistributionCodeRespDTO> getDistributionCodeLabel(String code);

    /**
     * 获取发货单
     * 
     * @param code
     * @return
     */
    Future<DispatchListRespDTO> getDispatchList(String code);

    Future<KDNOrderOnlineResp> getWaybill(String code);
}
