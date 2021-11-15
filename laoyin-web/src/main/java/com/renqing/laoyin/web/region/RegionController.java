package com.bat.laoyin.web.region;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.region.RegionService;
import com.bat.laoyin.api.region.dto.RegionQry;
import com.bat.laoyin.dao.region.dataobject.RegionDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 省市区表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-08-17
 */
@Api(tags = "省市区表", value = "RegionController")
@RestController
@RequestMapping("/v1/regions")
public class RegionController {

    @Resource
    private RegionService service;

    @GetMapping()
    @ApiOperation(value = "获取 省市区表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<RegionDO>> listAll(RegionQry qry) {
        IPage<RegionDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    // @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 省市区表 单个或多个资源", notes = "")
    public Response<List<RegionDO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.listByIds(ids));
    }

    // @PostMapping()
    @ApiOperation(value = "新增 省市区表 单个资源", notes = "")
    public Response<RegionDO> create(@RequestBody RegionDO aDo) {
        service.save(aDo);
        return Response.of(aDo);
    }

    // @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 省市区表 单个资源", notes = "")
    public Response<RegionDO> updatePutById(@PathVariable Integer id, @RequestBody RegionDO newObj) {
        newObj.setId(id);
        service.updateById(newObj);
        return Response.of(newObj);
    }

    // @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 省市区表 单个资源", notes = "")
    public Response<RegionDO> updatePatchById(@PathVariable Integer id, @RequestBody RegionDO newObj) {
        newObj.setId(id);
        RegionDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.updateById(oldObj);
        return Response.of(oldObj);
    }

    // @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 省市区表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

}
