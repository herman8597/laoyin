package com.bat.laoyin.service.sendgoods.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.sendgoods.dto.SendGoodsDistributionModeQry;
import com.bat.laoyin.api.sendgoods.dto.data.SendGoodsDistributionModeDTO;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsDistributionModeDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class SendGoodsDistributionModeConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static SendGoodsDistributionModeDO toSendGoodsDistributionModeDO(Object cmd, Short operationType) {
        SendGoodsDistributionModeDO aDo = new SendGoodsDistributionModeDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static SendGoodsDistributionModeDO toSendGoodsDistributionModeDO(SendGoodsDistributionModeQry qry) {
        SendGoodsDistributionModeDO aDo = new SendGoodsDistributionModeDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<SendGoodsDistributionModeDTO>
        toSendGoodsDistributionModeDTOList(List<SendGoodsDistributionModeDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(SendGoodsDistributionModeConvertor::toSendGoodsDistributionModeDTO)
                .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static SendGoodsDistributionModeDTO toSendGoodsDistributionModeDTO(SendGoodsDistributionModeDO aDo) {
        if (aDo != null) {
            SendGoodsDistributionModeDTO dto = new SendGoodsDistributionModeDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
