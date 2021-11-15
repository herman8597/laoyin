package com.bat.laoyin.api.user;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.user.dto.UserQry;
import com.bat.laoyin.api.user.dto.data.UserInfo;
import com.bat.laoyin.dao.user.dataobject.UserDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-08-07
 */
public interface UserService extends IServiceCmd<UserDO>, IServiceQry<UserDO> {

    IPage<UserDO> selectPageVo(IPage<UserDO> page, UserQry qry);

    List<UserDO> selectVo(UserQry qry);

    UserInfo login(UserQry qry);

    UserInfo getInfo(String token);

    void mySave(UserDO aDo);

    List<UserDO> myListByIds(List<Integer> ids);

    void myUpdateById(UserDO newObj);

    String getUserNameById(Integer userId);
}
