package com.bat.laoyin.service.customer.executor;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceCmdImpl;
import com.bat.laoyin.dao.customer.CustomerMapper;
import com.bat.laoyin.dao.customer.dataobject.CustomerDO;
import com.bat.laoyin.service.common.utils.NoUtils;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class CustomerCmdExe extends ServiceCmdImpl<CustomerMapper, CustomerDO> {

    @Resource
    private CustomerMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    public void mySave(CustomerDO aDo) {
        try {
            String appKey = NoUtils.getAppKey();
            aDo.setAppKey(appKey);
            aDo.setAppSecret(NoUtils.getAppSecret(appKey));
            myInsert(aDo);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 如果重复递归 直到不重复为止
     * 
     * @param aDo
     */
    private void myInsert(CustomerDO aDo) {
        try {
            mapper.insert(aDo);
        } catch (DuplicateKeyException e) {
            final String localizedMessage = e.getLocalizedMessage();
            if (localizedMessage.contains("Duplicate entry") && localizedMessage.contains("uk_app_key")) {
                mySave(aDo);
            }
        }
    }

    public void resetAppSecret(Integer id) {
        CustomerDO customerDO = mapper.selectById(id);
        String appKey = customerDO.getAppKey();
        customerDO.setAppSecret(NoUtils.getAppSecret(appKey));
        mapper.updateById(customerDO);
    }
}
