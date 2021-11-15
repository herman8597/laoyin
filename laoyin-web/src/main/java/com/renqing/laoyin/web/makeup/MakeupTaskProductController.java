package com.bat.laoyin.web.makeup;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.makeup.MakeupTaskProductService;
import com.bat.laoyin.api.makeup.dto.MakeupTaskProductQry;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskProductDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 拼版任务与产品关联表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Api(tags = "拼版任务与产品关联表", value = "MakeupTaskProductController")
// @RestController
@RequestMapping("/v1/makeuptaskproducts")
public class MakeupTaskProductController {

    @Resource
    private MakeupTaskProductService service;

    @GetMapping()
    @ApiOperation(value = "获取 拼版任务与产品关联表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<MakeupTaskProductDO>> listAll(MakeupTaskProductQry qry) {
        IPage<MakeupTaskProductDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 拼版任务与产品关联表 单个或多个资源", notes = "")
    public Response<List<MakeupTaskProductDO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.listByIds(ids));
    }

    @PostMapping()
    @ApiOperation(value = "新增 拼版任务与产品关联表 单个资源", notes = "")
    public Response<MakeupTaskProductDO> create(@RequestBody MakeupTaskProductDO aDo) {
        service.save(aDo);
        return Response.of(aDo);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 拼版任务与产品关联表 单个资源", notes = "")
    public Response<MakeupTaskProductDO> updatePutById(@PathVariable Integer id,
        @RequestBody MakeupTaskProductDO newObj) {
        newObj.setId(id);
        service.updateById(newObj);
        return Response.of(newObj);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 拼版任务与产品关联表 单个资源", notes = "")
    public Response<MakeupTaskProductDO> updatePatchById(@PathVariable Integer id,
        @RequestBody MakeupTaskProductDO newObj) {
        newObj.setId(id);
        MakeupTaskProductDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.updateById(oldObj);
        return Response.of(oldObj);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 拼版任务与产品关联表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

}
