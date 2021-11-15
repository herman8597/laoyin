package com.bat.laoyin.web.product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.product.ProductService;
import com.bat.laoyin.api.product.dto.ProductQry;
import com.bat.laoyin.dao.product.dataobject.ProductDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 产品表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-08-30
 */
@Api(tags = "产品表", value = "ProductController")
@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Resource
    private ProductService service;

    @GetMapping()
    @ApiOperation(value = "获取 产品表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<ProductDO>> listAll(ProductQry qry) {
        IPage<ProductDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 产品表 单个或多个资源", notes = "")
    public Response<List<ProductDO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.listByIds(ids));
    }

    @PostMapping()
    @ApiOperation(value = "新增 产品表 多个资源", notes = "")
    public Response<List<ProductDO>> create(@RequestBody List<ProductDO> aDos) {
        service.mySaveList(aDos);
        return Response.of(aDos);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 产品表 单个资源", notes = "")
    public Response<ProductDO> updatePutById(@PathVariable Integer id, @RequestBody ProductDO newObj) {
        newObj.setId(id);
        service.myUpdateById(newObj);
        return Response.of(newObj);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 产品表 单个资源", notes = "")
    public Response<ProductDO> updatePatchById(@PathVariable Integer id, @RequestBody ProductDO newObj) {
        newObj.setId(id);
        ProductDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.myUpdateById(oldObj);
        return Response.of(oldObj);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 产品表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

}
