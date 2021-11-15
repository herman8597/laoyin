package com.bat.laoyin.web.sendgoods;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.sendgoods.DispatchListService;
import com.bat.laoyin.api.sendgoods.dto.DispatchListQry;
import com.bat.laoyin.api.sendgoods.dto.data.DispatchListDTO;
import com.bat.laoyin.dao.sendgoods.dataobject.DispatchListDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 发货单表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-10-09
 */
@Api(tags = "发货单表", value = "DispatchListController")
@RestController
@RequestMapping("/v1/dispatchLists")
public class DispatchListController {

    @Resource
    private DispatchListService service;

    @GetMapping()
    @ApiOperation(value = "获取 发货单表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<DispatchListDTO>> listAll(DispatchListQry qry) {
        IPage<DispatchListDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    // @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 发货单表 单个或多个资源", notes = "")
    public Response<List<DispatchListDO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.listByIds(ids));
    }

    // @PostMapping()
    @ApiOperation(value = "新增 发货单表 单个资源", notes = "")
    public Response<DispatchListDO> create(@RequestBody DispatchListDO aDo) {
        service.save(aDo);
        return Response.of(aDo);
    }

    // @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 发货单表 单个资源", notes = "")
    public Response<DispatchListDO> updatePutById(@PathVariable Integer id, @RequestBody DispatchListDO newObj) {
        newObj.setId(id);
        service.updateById(newObj);
        return Response.of(newObj);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 发货单表 单个资源", notes = "")
    public Response<DispatchListDO> updatePatchById(@PathVariable Integer id, @RequestBody DispatchListDO newObj) {
        newObj.setId(id);
        DispatchListDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.updateById(oldObj);
        return Response.of(oldObj);
    }

    // @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 发货单表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

}
