package com.bat.laoyin.service.common.user.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.user.dto.UserQry;
import com.bat.laoyin.api.user.dto.data.UserDTO;
import com.bat.laoyin.dao.user.dataobject.UserDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class UserConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static UserDO toUserDO(Object cmd, Short operationType) {
        UserDO aDo = new UserDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static UserDO toUserDO(UserQry qry) {
        UserDO aDo = new UserDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<UserDTO> toUserDTOList(List<UserDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(UserConvertor::toUserDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static UserDTO toUserDTO(UserDO aDo) {
        if (aDo != null) {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
