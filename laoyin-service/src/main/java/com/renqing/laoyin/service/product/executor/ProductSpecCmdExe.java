package com.bat.laoyin.service.product.executor;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.bat.laoyin.dao.product.ProductMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.api.product.dto.ProductSpecCmd;
import com.bat.laoyin.dao.base.BaseDO;
import com.bat.laoyin.dao.product.ProductFamilyMapper;
import com.bat.laoyin.dao.product.ProductSpecMapper;
import com.bat.laoyin.dao.product.dataobject.ProductSpecDO;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class ProductSpecCmdExe extends ServiceCmdImpl<ProductSpecMapper, ProductSpecDO> {

    @Resource
    private ProductSpecMapper mapper;

    @Resource
    private ProductFamilyMapper familyMapper;

    @Resource
    private ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    public void mySaveList(ProductSpecCmd aDos) {
        try {
            familyMapper.insert(aDos.getFamilyDO());
            List<ProductSpecDO> collect = aDos.getSpecDOS().stream()
                .peek(specDO -> specDO.setProductFamilyId(aDos.getFamilyDO().getId())).collect(Collectors.toList());
            saveBatch(collect);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void myUpdateList(ProductSpecCmd aDos) {
        try {
            familyMapper.updateById(aDos.getFamilyDO());
            // 删除后 重新保存
            List<ProductSpecDO> specDOS = new LambdaQueryChainWrapper<>(getBaseMapper())
                .eq(ProductSpecDO::getProductFamilyId, aDos.getFamilyDO().getId()).list();
            List<Integer> ids = specDOS.stream().map(BaseDO::getId).collect(Collectors.toList());
            mapper.deleteBatchIds(ids);
            List<ProductSpecDO> collect = aDos.getSpecDOS().stream()
                .peek(specDO -> specDO.setProductFamilyId(aDos.getFamilyDO().getId())).collect(Collectors.toList());
            saveBatch(collect);
            collect.forEach(specDO -> {
                productMapper.updateSpecNameBySpecId(specDO.getId(),specDO.getSimpleSpecName());
            });
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
