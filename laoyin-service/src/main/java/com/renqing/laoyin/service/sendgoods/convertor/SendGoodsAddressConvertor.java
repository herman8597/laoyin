package com.bat.laoyin.service.sendgoods.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.sendgoods.dto.SendGoodsAddressQry;
import com.bat.laoyin.api.sendgoods.dto.data.SendGoodsAddressDTO;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsAddressDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class SendGoodsAddressConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static SendGoodsAddressDO toSendGoodsAddressDO(Object cmd, Short operationType) {
        SendGoodsAddressDO aDo = new SendGoodsAddressDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static SendGoodsAddressDO toSendGoodsAddressDO(SendGoodsAddressQry qry) {
        SendGoodsAddressDO aDo = new SendGoodsAddressDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<SendGoodsAddressDTO> toSendGoodsAddressDTOList(List<SendGoodsAddressDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(SendGoodsAddressConvertor::toSendGoodsAddressDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static SendGoodsAddressDTO toSendGoodsAddressDTO(SendGoodsAddressDO aDo) {
        if (aDo != null) {
            SendGoodsAddressDTO dto = new SendGoodsAddressDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
