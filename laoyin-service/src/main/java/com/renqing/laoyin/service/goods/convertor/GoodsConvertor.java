package com.bat.laoyin.service.goods.convertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.bat.laoyin.api.goods.dto.GoodsQry;
import com.bat.laoyin.api.goods.dto.data.GoodsDTO;
import com.bat.laoyin.dao.goods.dataobject.GoodsDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/6 20:25
 */
public class GoodsConvertor {

    /**
     * CMD 转 DO
     * 
     * @param cmd
     * @param operationType
     * @return
     */
    public static GoodsDO toGoodsDO(Object cmd, Short operationType) {
        GoodsDO aDo = new GoodsDO();
        BeanUtils.copyProperties(cmd, aDo);
        if (operationType.equals(Constant.OPERATION_TYPE_CREATE)) {
            aDo.setId(null);
        }
        Date date = new Date();
        aDo.setCreatedAt(date);
        aDo.setUpdatedAt(date);
        return aDo;
    }

    public static GoodsDO toGoodsDO(GoodsQry qry) {
        GoodsDO aDo = new GoodsDO();
        BeanUtils.copyProperties(qry, aDo);
        return aDo;
    }

    /**
     * do列表转dto列表
     *
     * @param dos
     * @return
     */
    public static List<GoodsDTO> toGoodsDTOList(List<GoodsDO> dos) {
        if (!CollectionUtils.isEmpty(dos)) {
            return dos.stream().map(GoodsConvertor::toGoodsDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * do转dto
     *
     * @param aDo
     * @return
     */
    public static GoodsDTO toGoodsDTO(GoodsDO aDo) {
        if (aDo != null) {
            GoodsDTO dto = new GoodsDTO();
            BeanUtils.copyProperties(aDo, dto);
            return dto;
        }
        return null;
    }
}
