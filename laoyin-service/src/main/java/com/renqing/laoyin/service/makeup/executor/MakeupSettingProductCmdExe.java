package com.bat.laoyin.service.makeup.executor;

import static com.bat.laoyin.api.common.ErrorCode.B_PRODUCT_HAS_BEEN_BIND_MAKEUP;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.api.common.LaoYinException;
import com.bat.laoyin.api.makeup.dto.MakeupSettingProductCmd;
import com.bat.laoyin.dao.makeup.MakeupSettingProductMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingProductDO;
import com.bat.laoyin.service.common.constants.Constant;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class MakeupSettingProductCmdExe extends ServiceCmdImpl<MakeupSettingProductMapper, MakeupSettingProductDO> {

    public void mySave(MakeupSettingProductCmd aDo) {
        Integer makeupSettingId = aDo.getMakeupSettingId();
        List<Integer> productIds = aDo.getProductIds();
        List<MakeupSettingProductDO> makeupSettingProductDOS = baseMapper.listByProductIds(productIds);
        if (!CollectionUtils.isEmpty(makeupSettingProductDOS)) {
            List<Integer> errorProductIds =
                makeupSettingProductDOS.stream().map(MakeupSettingProductDO::getProductId).collect(Collectors.toList());
            String collect1 = errorProductIds.stream().map(String::valueOf).collect(Collectors.joining(","));
            throw LaoYinException.buildException(B_PRODUCT_HAS_BEEN_BIND_MAKEUP, "已经参与拼版:" + collect1);
        }
        List<MakeupSettingProductDO> collect = productIds.stream().map(integer -> {
            MakeupSettingProductDO settingProductDO = new MakeupSettingProductDO();
            settingProductDO.setProductId(integer);
            settingProductDO.setMakeupSettingId(makeupSettingId);
            settingProductDO.setStatus(Constant.OPEN_YES);
            return settingProductDO;
        }).collect(Collectors.toList());
        saveBatch(collect);
    }
}
