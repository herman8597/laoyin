package com.bat.laoyin.web.user;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.user.UserRoleService;
import com.bat.laoyin.api.user.dto.UserRoleQry;
import com.bat.laoyin.dao.user.dataobject.UserRoleDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 人员角色关联表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-08-16
 */
@Api(tags = "人员角色关联表", value = "UserRoleController")
@RestController
@RequestMapping("/v1/userRoles")
public class UserRoleController {

    @Resource
    private UserRoleService service;

    @GetMapping()
    @ApiOperation(value = "获取 人员角色关联表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<UserRoleDO>> listAll(UserRoleQry qry) {
        IPage<UserRoleDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 人员角色关联表 单个或多个资源", notes = "")
    public Response<List<UserRoleDO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.listByIds(ids));
    }

    @PostMapping()
    @ApiOperation(value = "新增 人员角色关联表 单个资源", notes = "")
    public Response<UserRoleDO> create(@RequestBody UserRoleDO aDo) {
        service.save(aDo);
        return Response.of(aDo);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 人员角色关联表 单个资源", notes = "")
    public Response<UserRoleDO> updatePutById(@PathVariable Integer id, @RequestBody UserRoleDO newObj) {
        newObj.setId(id);
        service.updateById(newObj);
        return Response.of(newObj);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 人员角色关联表 单个资源", notes = "")
    public Response<UserRoleDO> updatePatchById(@PathVariable Integer id, @RequestBody UserRoleDO newObj) {
        newObj.setId(id);
        UserRoleDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.updateById(oldObj);
        return Response.of(oldObj);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 人员角色关联表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

}
