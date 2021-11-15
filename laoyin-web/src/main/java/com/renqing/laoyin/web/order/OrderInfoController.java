package com.bat.laoyin.web.order;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.order.OrderInfoService;
import com.bat.laoyin.api.order.dto.OrderInfoQry;
import com.bat.laoyin.api.order.dto.data.OrderInfoDTO;
import com.bat.laoyin.dao.order.dataobject.OrderInfoDO;
import com.bat.laoyin.web.base.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 订单(基本信息)表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Api(tags = "订单(基本信息)表", value = "OrderInfoController")
@RestController
@RequestMapping("/v1/orderInfo")
public class OrderInfoController {

    @Resource
    private OrderInfoService service;

    @GetMapping()
    @ApiOperation(value = "获取 订单(基本信息)表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<OrderInfoDTO>> listAll(OrderInfoQry qry) {
        IPage<OrderInfoDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 订单(基本信息)表 单个或多个资源", notes = "")
    public Response<List<OrderInfoDTO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.myListByIds(ids));
    }

    // @PostMapping()
    // @ApiOperation(value = "新增 订单(基本信息)表 单个资源", notes = "")
    // public Response<OrderInfoDO> create(@RequestBody OrderInfoDO aDo) {
    // service.save(aDo);
    // return Response.of(aDo);
    // }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 订单(基本信息)表 单个资源", notes = "")
    public Response updatePutById(@PathVariable Integer id, @RequestBody OrderInfoDTO newObj) {
        newObj.setId(id);
        service.myUpdatePutById(newObj);
        return Response.buildSuccess();
    }

    // @PatchMapping("/{id}")
    // @ApiOperation(value = "根据主键增量更新 订单(基本信息)表 单个资源", notes = "")
    // public Response<OrderInfoDO> updatePatchById(@PathVariable Integer id, @RequestBody OrderInfoDTO newObj) {
    // newObj.setId(id);
    // service.myUpdatePatchById(newObj);
    // return Response.buildSuccess();
    // }

    // @DeleteMapping("/{id}")
    // @ApiOperation(value = "根据主键(id|id;id;id)删除 订单(基本信息)表 单个或多个资源", notes = "")
    // public Response deleteById(@PathVariable String id) {
    // List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
    // return Response.of(service.removeByIds(ids));
    // }

}
