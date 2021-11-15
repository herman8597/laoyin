package com.bat.laoyin.service.goods.executor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.goods.dto.GoodsQry;
import com.bat.laoyin.api.goods.dto.data.GoodsDTO;
import com.bat.laoyin.dao.goods.GoodsMapper;
import com.bat.laoyin.dao.goods.dataobject.GoodsDO;
import com.bat.laoyin.dao.product.ProductMapper;
import com.bat.laoyin.dao.product.dataobject.ProductDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.goods.convertor.GoodsConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class GoodsQryExe extends ServiceQryImpl<GoodsMapper, GoodsDO> {
    @Resource
    private GoodsMapper mapper;

    @Resource
    private ProductMapper productMapper;

    public IPage<GoodsDO> selectPageVo(IPage<GoodsDO> page, GoodsQry qry) {
        GoodsDO aDo = GoodsConvertor.toGoodsDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        Map<String, Object> map = new HashMap<>(16);
        map.put("matchStartTime", qry.getMatchStartTime());
        map.put("matchEndTime", qry.getMatchEndTime());
        return mapper.selectPageVo(page, aDo, sort, map);
    }

    public List<GoodsDO> selectVo(GoodsQry qry) {
        GoodsDO aDo = GoodsConvertor.toGoodsDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }

    public IPage<GoodsDTO> listMatch(IPage<GoodsDO> page, GoodsQry qry) {
        IPage pageVo = selectPageVo(page, qry);
        List<GoodsDO> records = pageVo.getRecords();
        List<GoodsDTO> dtos = records.stream().map(goodsDO -> {
            GoodsDTO dto = GoodsConvertor.toGoodsDTO(goodsDO);
            String productCode = dto.getProductCode();
            if (StringUtils.isNotBlank(productCode)) {
                ProductDO byCode = productMapper.getByCode(productCode);
                if (byCode != null) {
                    dto.setProductName(byCode.getName());
                    dto.setProductSpec(byCode.getProductSpecName());
                    dto.setProductUnitName(byCode.getUnitName());
                }
            }
            return dto;
        }).collect(Collectors.toList());
        pageVo.setRecords(dtos);
        return pageVo;
    }
}
