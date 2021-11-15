package com.bat.laoyin.service.product.executor;

import static com.bat.laoyin.service.common.constants.Constant.UNKNOWN;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.product.dto.ProductFamilyQry;
import com.bat.laoyin.api.product.dto.data.ProductFamilyDTO;
import com.bat.laoyin.dao.product.ProductCategoryMapper;
import com.bat.laoyin.dao.product.ProductFamilyMapper;
import com.bat.laoyin.dao.product.ProductMapper;
import com.bat.laoyin.dao.product.dataobject.ProductCategoryDO;
import com.bat.laoyin.dao.product.dataobject.ProductDO;
import com.bat.laoyin.dao.product.dataobject.ProductFamilyDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.product.convertor.ProductFamilyConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class ProductFamilyQryExe extends ServiceQryImpl<ProductFamilyMapper, ProductFamilyDO> {
    @Resource
    private ProductFamilyMapper mapper;

    @Resource
    private ProductCategoryMapper categoryMapper;

    @Resource
    private ProductMapper productMapper;

    public IPage<ProductFamilyDTO> selectPageVo(IPage<ProductFamilyDO> page, ProductFamilyQry qry) {
        ProductFamilyDO aDo = ProductFamilyConvertor.toProductFamilyDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        // 处理 产品名称 获取品类id集合
        List<Integer> productFamilyIds = null;
        if (StringUtils.isNotBlank(qry.getProductName())) {
            LambdaQueryWrapper<ProductDO> wrapper = new LambdaQueryWrapper<>();
            wrapper.likeLeft(ProductDO::getName, qry.getProductName());
            List<ProductDO> productDOS = productMapper.selectList(wrapper);
            productFamilyIds = productDOS.stream().map(ProductDO::getProductFamilyId).collect(Collectors.toList());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("createdStartTime", qry.getCreatedStartTime());
        map.put("createdEndTime", qry.getCreatedEndTime());
        IPage pageVo = mapper.selectPageVo(page, aDo, sort, productFamilyIds,map);
        // 拼装产品品类名称
        List<ProductFamilyDO> records = pageVo.getRecords();
        List<ProductFamilyDTO> collect = records.stream().map(productFamilyDO -> {
            ProductFamilyDTO dto = ProductFamilyConvertor.toProductFamilyDTO(productFamilyDO);
            ProductCategoryDO productCategoryDO = categoryMapper.selectById(dto.getProductCategoryId());
            if (productCategoryDO != null) {
                dto.setProductCategoryName(productCategoryDO.getName());
            } else {
                dto.setProductCategoryName(UNKNOWN);
            }
            return dto;
        }).collect(Collectors.toList());
        pageVo.setRecords(collect);
        return pageVo;
    }

    public List<ProductFamilyDO> selectVo(ProductFamilyQry qry) {
        ProductFamilyDO aDo = ProductFamilyConvertor.toProductFamilyDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
