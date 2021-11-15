package com.bat.laoyin.service.common.region.convertor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.region.dto.RegionQry;
import com.bat.laoyin.api.region.dto.data.RegionDTO;
import com.bat.laoyin.dao.region.dataobject.RegionDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class RegionConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static RegionDO toRegionDO(Object cmd, Short operationType) {
        RegionDO aDo = new RegionDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        return aDo;
    }

    public static RegionDO toRegionDO(RegionQry qry) {
        RegionDO aDo = new RegionDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<RegionDTO> toRegionDTOList(List<RegionDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(RegionConvertor::toRegionDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static RegionDTO toRegionDTO(RegionDO aDo) {
        if (aDo != null) {
            RegionDTO dto = new RegionDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
