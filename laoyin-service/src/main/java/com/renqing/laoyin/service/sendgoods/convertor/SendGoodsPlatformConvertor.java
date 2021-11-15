package com.bat.laoyin.service.sendgoods.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.sendgoods.dto.SendGoodsPlatformQry;
import com.bat.laoyin.api.sendgoods.dto.data.SendGoodsPlatformDTO;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsPlatformDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class SendGoodsPlatformConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static SendGoodsPlatformDO toSendGoodsPlatformDO(Object cmd, Short operationType) {
        SendGoodsPlatformDO aDo = new SendGoodsPlatformDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static SendGoodsPlatformDO toSendGoodsPlatformDO(SendGoodsPlatformQry qry) {
        SendGoodsPlatformDO aDo = new SendGoodsPlatformDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<SendGoodsPlatformDTO> toSendGoodsPlatformDTOList(List<SendGoodsPlatformDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(SendGoodsPlatformConvertor::toSendGoodsPlatformDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static SendGoodsPlatformDTO toSendGoodsPlatformDTO(SendGoodsPlatformDO aDo) {
        if (aDo != null) {
            SendGoodsPlatformDTO dto = new SendGoodsPlatformDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
