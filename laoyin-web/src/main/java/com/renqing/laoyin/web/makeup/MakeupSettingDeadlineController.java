package com.bat.laoyin.web.makeup;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.makeup.MakeupSettingDeadlineService;
import com.bat.laoyin.api.makeup.dto.MakeupSettingDeadlineQry;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDeadlineDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 拼版设置截止时间关联表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Api(tags = "拼版设置截止时间关联表", value = "MakeupSettingDeadlineController")
// @RestController
@RequestMapping("/v1/makeupSettingDeadlines")
public class MakeupSettingDeadlineController {

    @Resource
    private MakeupSettingDeadlineService service;

    @GetMapping()
    @ApiOperation(value = "获取 拼版设置截止时间关联表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<MakeupSettingDeadlineDO>> listAll(MakeupSettingDeadlineQry qry) {
        IPage<MakeupSettingDeadlineDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 拼版设置截止时间关联表 单个或多个资源", notes = "")
    public Response<List<MakeupSettingDeadlineDO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.listByIds(ids));
    }

    @PostMapping()
    @ApiOperation(value = "新增 拼版设置截止时间关联表 单个资源", notes = "")
    public Response<MakeupSettingDeadlineDO> create(@RequestBody MakeupSettingDeadlineDO aDo) {
        service.save(aDo);
        return Response.of(aDo);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 拼版设置截止时间关联表 单个资源", notes = "")
    public Response<MakeupSettingDeadlineDO> updatePutById(@PathVariable Integer id,
        @RequestBody MakeupSettingDeadlineDO newObj) {
        newObj.setId(id);
        service.updateById(newObj);
        return Response.of(newObj);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 拼版设置截止时间关联表 单个资源", notes = "")
    public Response<MakeupSettingDeadlineDO> updatePatchById(@PathVariable Integer id,
        @RequestBody MakeupSettingDeadlineDO newObj) {
        newObj.setId(id);
        MakeupSettingDeadlineDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.updateById(oldObj);
        return Response.of(oldObj);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 拼版设置截止时间关联表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

}
