package com.bat.laoyin.service.common.role.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.role.dto.RoleQry;
import com.bat.laoyin.api.role.dto.data.RoleDTO;
import com.bat.laoyin.dao.role.dataobject.RoleDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class RoleConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static RoleDO toRoleDO(Object cmd, Short operationType) {
        RoleDO aDo = new RoleDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static RoleDO toRoleDO(RoleQry qry) {
        RoleDO aDo = new RoleDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<RoleDTO> toRoleDTOList(List<RoleDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(RoleConvertor::toRoleDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static RoleDTO toRoleDTO(RoleDO aDo) {
        if (aDo != null) {
            RoleDTO dto = new RoleDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
