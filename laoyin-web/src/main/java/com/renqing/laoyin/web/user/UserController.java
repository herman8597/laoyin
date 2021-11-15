package com.bat.laoyin.web.user;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.user.UserService;
import com.bat.laoyin.api.user.dto.UserQry;
import com.bat.laoyin.api.user.dto.data.UserInfo;
import com.bat.laoyin.dao.user.dataobject.UserDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-08-07
 */
@Api(tags = "用户表", value = "UserController")
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Resource
    private UserService service;

    @GetMapping()
    @ApiOperation(value = "获取 用户表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<UserDO>> listAll(UserQry qry) {
        IPage<UserDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 用户表 单个或多个资源", notes = "")
    public Response<List<UserDO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.myListByIds(ids));
    }

    @PostMapping()
    @ApiOperation(value = "新增 用户表 单个资源", notes = "")
    public Response<UserDO> create(@RequestBody UserDO aDo) {
        service.mySave(aDo);
        return Response.of(aDo);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 用户表 单个资源", notes = "")
    public Response<UserDO> updatePutById(@PathVariable Integer id, @RequestBody UserDO newObj) {
        newObj.setId(id);
        service.myUpdateById(newObj);
        return Response.of(newObj);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 用户表 单个资源", notes = "")
    public Response<UserDO> updatePatchById(@PathVariable Integer id, @RequestBody UserDO newObj) {
        newObj.setId(id);
        UserDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.updateById(oldObj);
        return Response.of(oldObj);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 用户表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口", notes = "")
    public Response<UserInfo> login(@RequestBody UserQry qry) {
        return Response.of(service.login(qry));
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户登出接口", notes = "")
    public Response logout() {
        return Response.buildSuccess();
    }

    @PostMapping("/info")
    @ApiOperation(value = "获取用户信息", notes = "")
    public Response<UserInfo> getInfo(@RequestBody Map<String, String> token) {
        return Response.of(service.getInfo(token.get("token")));
    }

}
