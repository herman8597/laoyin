package com.bat.laoyin.service.customer.executor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.customer.dto.CustomerQry;
import com.bat.laoyin.dao.customer.CustomerMapper;
import com.bat.laoyin.dao.customer.dataobject.CustomerDO;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.customer.convertor.CustomerConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class CustomerQryExe extends ServiceQryImpl<CustomerMapper, CustomerDO> {
    @Resource
    private CustomerMapper mapper;

    public IPage<CustomerDO> selectPageVo(IPage<CustomerDO> page, CustomerQry qry) {
        CustomerDO aDo = CustomerConvertor.toCustomerDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(page, aDo, sort);
    }

    public List<CustomerDO> selectVo(CustomerQry qry) {
        CustomerDO aDo = CustomerConvertor.toCustomerDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }
}
