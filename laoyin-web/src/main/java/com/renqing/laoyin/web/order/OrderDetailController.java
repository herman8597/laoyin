package com.bat.laoyin.web.order;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.order.OrderDetailService;
import com.bat.laoyin.api.order.dto.OrderDetailQry;
import com.bat.laoyin.dao.order.dataobject.OrderDetailDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 订单明细表 前端控制器
 *
 * 代码生成 暂时用不到
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Api(tags = "订单明细表", value = "OrderDetailController")
@RestController
@RequestMapping("/v1/orderDetails")
public class OrderDetailController {

    @Resource
    private OrderDetailService service;

    @GetMapping()
    @ApiOperation(value = "获取 订单明细表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<OrderDetailDO>> listAll(OrderDetailQry qry) {
        IPage<OrderDetailDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    // @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 订单明细表 单个或多个资源", notes = "")
    public Response<List<OrderDetailDO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.listByIds(ids));
    }

    // @PostMapping()
    @ApiOperation(value = "新增 订单明细表 单个资源", notes = "")
    public Response<OrderDetailDO> create(@RequestBody OrderDetailDO aDo) {
        service.save(aDo);
        return Response.of(aDo);
    }

    // @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 订单明细表 单个资源", notes = "")
    public Response<OrderDetailDO> updatePutById(@PathVariable Integer id, @RequestBody OrderDetailDO newObj) {
        newObj.setId(id);
        service.updateById(newObj);
        return Response.of(newObj);
    }

    // @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 订单明细表 单个资源", notes = "")
    public Response<OrderDetailDO> updatePatchById(@PathVariable Integer id, @RequestBody OrderDetailDO newObj) {
        newObj.setId(id);
        OrderDetailDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.updateById(oldObj);
        return Response.of(oldObj);
    }

    // @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 订单明细表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

}
