package com.bat.laoyin.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.common.ErrorCode;
import com.bat.laoyin.api.common.LaoYinException;
import com.bat.laoyin.api.common.utils.TokenUtils;
import com.bat.laoyin.api.user.UserService;
import com.bat.laoyin.api.user.dto.UserQry;
import com.bat.laoyin.api.user.dto.data.UserInfo;
import com.bat.laoyin.dao.role.dataobject.RoleDO;
import com.bat.laoyin.dao.user.UserMapper;
import com.bat.laoyin.dao.user.dataobject.UserDO;
import com.bat.laoyin.service.common.constants.Constant;
import com.bat.laoyin.service.common.user.executor.UserCmdExe;
import com.bat.laoyin.service.common.user.executor.UserQryExe;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-08-07
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Resource
    private UserCmdExe cmdExe;

    @Resource
    private UserQryExe qryExe;

    public UserServiceImpl(@Autowired UserCmdExe serviceCmd, @Autowired UserQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    public static Claims getClaims(String token) {
        boolean verify = TokenUtils.verify(token);
        if (!verify) {
            throw LaoYinException.buildException(ErrorCode.B_TOKEN_IS_INVALID);
        }
        return TokenUtils.parseJWT(token);
    }

    @Override
    public IPage<UserDO> selectPageVo(IPage<UserDO> page, UserQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<UserDO> selectVo(UserQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void mySave(UserDO aDo) {
        cmdExe.mySave(aDo);
    }

    @Override
    public List<UserDO> myListByIds(List<Integer> ids) {
        return qryExe.myListByIds(ids);
    }

    @Override
    @CacheUpdate(name = "userCache-", key = "#newObj.id", value = "#newObj.realName")
    public void myUpdateById(UserDO newObj) {
        cmdExe.myUpdateById(newObj);
    }

    @Override
    @Cached(name = "userCache-", key = "#userId", expire = 600, cacheType = CacheType.LOCAL)
    public String getUserNameById(Integer userId) {
        String userNameById = qryExe.getUserNameById(userId);
        log.info("通过数据库查询 id：{} 用户名: {}", userId, userNameById);
        return userNameById;
    }

    @Override
    public UserInfo login(UserQry qry) {
        List<UserDO> userDOS = selectVo(qry);
        if (!CollectionUtils.isEmpty(userDOS) && userDOS.size() == 1) {
            UserDO userDO = userDOS.get(0);
            System.out.println(userDO);
            if (userDO.getStatus().equals(Constant.OPEN_YES)) {
                UserInfo info = new UserInfo();
                info.setId(userDO.getId());
                info.setTenantId(userDO.getTenantId());
                info.setName(userDO.getUserName());
                List<RoleDO> roleInfo = qryExe.getRoleInfo(userDO.getId(), userDO.getTenantId());
                info.setRoles(roleInfo.stream().map(RoleDO::getNameEn).collect(Collectors.toList()));
                Map<String, Object> map = JSONObject.parseObject(JSON.toJSONString(info));
                info.setToken(TokenUtils.createJWT(map));
                return info;
            } else {
                throw LaoYinException.buildException(ErrorCode.B_USER_DISABLE);
            }
        }
        throw LaoYinException.buildException(ErrorCode.B_USER_NAME_OR_PASSWORD);
    }

    @Override
    public UserInfo getInfo(String token) {
        Claims claims = getClaims(token);
        Integer id = (Integer)claims.get("id");
        UserInfo info = new UserInfo();
        UserDO byId = qryExe.getById(id);
        info.setId(id);
        info.setTenantId(byId.getTenantId());
        info.setName(byId.getUserName());
        info.setRoles((ArrayList)claims.get("roles"));
        info.setToken(token);
        return info;
    }

}