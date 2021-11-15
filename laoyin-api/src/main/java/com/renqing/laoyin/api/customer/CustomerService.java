package com.bat.laoyin.api.customer;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.customer.dto.CustomerQry;
import com.bat.laoyin.dao.customer.dataobject.CustomerDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-09-03
 */
public interface CustomerService extends IServiceCmd<CustomerDO>, IServiceQry<CustomerDO> {

    IPage<CustomerDO> selectPageVo(IPage<CustomerDO> page, CustomerQry qry);

    List<CustomerDO> selectVo(CustomerQry qry);

    /**
     * 自定义更新
     *
     * @param aDo
     */
    void mySave(CustomerDO aDo);

    /**
     * 更新用户秘钥
     * 
     * @param id
     */
    void resetAppSecret(Integer id);
}
