package com.bat.laoyin.web.customer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.customer.CustomerService;
import com.bat.laoyin.api.customer.dto.CustomerQry;
import com.bat.laoyin.dao.customer.dataobject.CustomerDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 租户客户表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-09-03
 */
@Api(tags = "租户客户表", value = "CustomerController")
@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    @Resource
    private CustomerService service;

    @GetMapping()
    @ApiOperation(value = "获取 租户客户表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<CustomerDO>> listAll(CustomerQry qry) {
        IPage<CustomerDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 租户客户表 单个或多个资源", notes = "")
    public Response<List<CustomerDO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.listByIds(ids));
    }

    @PostMapping()
    @ApiOperation(value = "新增 租户客户表 单个资源", notes = "")
    public Response<CustomerDO> create(@RequestBody CustomerDO aDo) {
        service.mySave(aDo);
        return Response.of(aDo);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 租户客户表 单个资源", notes = "")
    public Response<CustomerDO> updatePutById(@PathVariable Integer id, @RequestBody CustomerDO newObj) {
        newObj.setId(id);
        service.updateById(newObj);
        return Response.of(newObj);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 租户客户表 单个资源", notes = "")
    public Response<CustomerDO> updatePatchById(@PathVariable Integer id, @RequestBody CustomerDO newObj) {
        newObj.setId(id);
        CustomerDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.updateById(oldObj);
        return Response.of(oldObj);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 租户客户表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

    @PutMapping("/{id}/appSecret")
    @ApiOperation(value = "根据主键(id) 更新AppSecret", notes = "")
    public Response resetAppSecret(@PathVariable Integer id) {
        service.resetAppSecret(id);
        return Response.buildSuccess();
    }

}
