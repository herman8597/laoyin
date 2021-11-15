package com.bat.laoyin.service.makeup.executor;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.makeup.dto.MakeupSettingProductQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupSettingProductDTO;
import com.bat.laoyin.dao.makeup.MakeupSettingProductMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingProductDO;
import com.bat.laoyin.dao.product.ProductMapper;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.makeup.convertor.MakeupSettingProductConvertor;
import com.bat.laoyin.service.product.convertor.ProductConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class MakeupSettingProductQryExe extends ServiceQryImpl<MakeupSettingProductMapper, MakeupSettingProductDO> {
    @Resource
    private MakeupSettingProductMapper mapper;

    @Resource
    private ProductMapper productMapper;

    public IPage<MakeupSettingProductDTO> selectPageVo(IPage<MakeupSettingProductDO> page,
        MakeupSettingProductQry qry) {
        MakeupSettingProductDO aDo = MakeupSettingProductConvertor.toMakeupSettingProductDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        IPage iPage = mapper.selectPageVo(page, aDo, sort);
        List<MakeupSettingProductDO> records = iPage.getRecords();
        List<MakeupSettingProductDTO> collect = records.stream().map(o -> {
            MakeupSettingProductDTO dto = new MakeupSettingProductDTO();
            BeanUtils.copyProperties(o, dto);
            dto.setProducts(ProductConvertor.toProductDTO(productMapper.selectById(o.getProductId())));
            return dto;
        }).collect(Collectors.toList());
        iPage.setRecords(collect);
        return iPage;
    }

    public List<MakeupSettingProductDO> selectVo(MakeupSettingProductQry qry) {
        MakeupSettingProductDO aDo = MakeupSettingProductConvertor.toMakeupSettingProductDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
