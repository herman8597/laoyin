package com.bat.laoyin.service.common.tenant.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.tenant.dto.TenantQry;
import com.bat.laoyin.api.tenant.dto.data.TenantDTO;
import com.bat.laoyin.dao.tenant.dataobject.TenantDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class TenantConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static TenantDO toTenantDO(Object cmd, Short operationType) {
        TenantDO aDo = new TenantDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static TenantDO toTenantDO(TenantQry qry) {
        TenantDO aDo = new TenantDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<TenantDTO> toTenantDTOList(List<TenantDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(TenantConvertor::toTenantDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static TenantDTO toTenantDTO(TenantDO aDo) {
        if (aDo != null) {
            TenantDTO dto = new TenantDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
