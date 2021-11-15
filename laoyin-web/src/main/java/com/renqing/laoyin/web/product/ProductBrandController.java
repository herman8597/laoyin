package com.bat.laoyin.web.product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.product.ProductBrandService;
import com.bat.laoyin.api.product.dto.ProductBrandQry;
import com.bat.laoyin.dao.product.dataobject.ProductBrandDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 产品品牌表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
@Api(tags = "产品品牌表", value = "ProductBrandController")
@RestController
@RequestMapping("/v1/productBrands")
public class ProductBrandController {

    @Resource
    private ProductBrandService service;

    @GetMapping()
    @ApiOperation(value = "获取 产品品牌表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<ProductBrandDO>> listAll(ProductBrandQry qry) {
        IPage<ProductBrandDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 产品品牌表 单个或多个资源", notes = "")
    public Response<List<ProductBrandDO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.listByIds(ids));
    }

    @PostMapping()
    @ApiOperation(value = "新增 产品品牌表 单个资源", notes = "")
    public Response<ProductBrandDO> create(@RequestBody ProductBrandDO aDo) {
        service.save(aDo);
        return Response.of(aDo);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 产品品牌表 单个资源", notes = "")
    public Response<ProductBrandDO> updatePutById(@PathVariable Integer id, @RequestBody ProductBrandDO newObj) {
        newObj.setId(id);
        service.myUpdateById(newObj);
        return Response.of(newObj);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 产品品牌表 单个资源", notes = "")
    public Response<ProductBrandDO> updatePatchById(@PathVariable Integer id, @RequestBody ProductBrandDO newObj) {
        newObj.setId(id);
        ProductBrandDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.myUpdateById(oldObj);
        return Response.of(oldObj);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 产品品牌表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

}
