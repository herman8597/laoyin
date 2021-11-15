package com.bat.laoyin.service.common.user.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.user.dto.UserRoleQry;
import com.bat.laoyin.api.user.dto.data.UserRoleDTO;
import com.bat.laoyin.dao.user.dataobject.UserRoleDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class UserRoleConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static UserRoleDO toUserRoleDO(Object cmd, Short operationType) {
        UserRoleDO aDo = new UserRoleDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static UserRoleDO toUserRoleDO(UserRoleQry qry) {
        UserRoleDO aDo = new UserRoleDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<UserRoleDTO> toUserRoleDTOList(List<UserRoleDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(UserRoleConvertor::toUserRoleDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static UserRoleDTO toUserRoleDTO(UserRoleDO aDo) {
        if (aDo != null) {
            UserRoleDTO dto = new UserRoleDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
