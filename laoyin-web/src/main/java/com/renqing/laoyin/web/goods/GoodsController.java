package com.bat.laoyin.web.goods;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.goods.GoodsService;
import com.bat.laoyin.api.goods.dto.GoodsQry;
import com.bat.laoyin.api.goods.dto.data.GoodsDTO;
import com.bat.laoyin.dao.goods.dataobject.GoodsDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Api(tags = "商品表", value = "GoodsController")
@RestController
@RequestMapping("/v1/goodss")
public class GoodsController {

    @Resource
    private GoodsService service;

    // @GetMapping()
    @ApiOperation(value = "获取 商品表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<GoodsDO>> listAll(GoodsQry qry) {
        IPage<GoodsDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    @GetMapping()
    @ApiOperation(value = "获取 匹配列表(分页)", notes = "")
    public Response<IPage<GoodsDTO>> listMatch(GoodsQry qry) {
        IPage page = new Page<>(qry.getPage(), qry.getSize());
        IPage<GoodsDTO> iPage = service.listMatch(page, qry);
        return Response.of(iPage);
    }

    // @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 商品表 单个或多个资源", notes = "")
    public Response<List<GoodsDO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.listByIds(ids));
    }

    @PostMapping()
    @ApiOperation(value = "新增 商品表 单个资源", notes = "")
    public Response<GoodsDO> create(@RequestBody GoodsDO aDo) {
        service.mySave(aDo);
        return Response.of(aDo);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 商品表 单个资源", notes = "")
    public Response<GoodsDO> updatePutById(@PathVariable Integer id, @RequestBody GoodsDO newObj) {
        newObj.setId(id);
        service.updateById(newObj);
        return Response.of(newObj);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 商品表 单个资源", notes = "")
    public Response<GoodsDO> updatePatchById(@PathVariable Integer id, @RequestBody GoodsDO newObj) {
        newObj.setId(id);
        GoodsDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.updateById(oldObj);
        return Response.of(oldObj);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 商品表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

}
