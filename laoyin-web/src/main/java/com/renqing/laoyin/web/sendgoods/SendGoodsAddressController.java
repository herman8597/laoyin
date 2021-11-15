package com.bat.laoyin.web.sendgoods;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.sendgoods.SendGoodsAddressService;
import com.bat.laoyin.api.sendgoods.dto.SendGoodsAddressQry;
import com.bat.laoyin.dao.sendgoods.dataobject.SendGoodsAddressDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 发货地址表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-08-18
 */
@Api(tags = "发货地址表", value = "SendGoodsAddressController")
@RestController
@RequestMapping("/v1/sendGoodsAddresses")
public class SendGoodsAddressController {

    @Resource
    private SendGoodsAddressService service;

    @GetMapping()
    @ApiOperation(value = "获取 发货地址表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<SendGoodsAddressDO>> listAll(SendGoodsAddressQry qry) {
        IPage<SendGoodsAddressDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 发货地址表 单个或多个资源", notes = "")
    public Response<List<SendGoodsAddressDO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.listByIds(ids));
    }

    @PostMapping()
    @ApiOperation(value = "新增 发货地址表 单个资源", notes = "")
    public Response<SendGoodsAddressDO> create(@RequestBody SendGoodsAddressDO aDo) {
        service.save(aDo);
        return Response.of(aDo);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 发货地址表 单个资源", notes = "")
    public Response<SendGoodsAddressDO> updatePutById(@PathVariable Integer id,
        @RequestBody SendGoodsAddressDO newObj) {
        newObj.setId(id);
        service.updateById(newObj);
        return Response.of(newObj);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 发货地址表 单个资源", notes = "")
    public Response<SendGoodsAddressDO> updatePatchById(@PathVariable Integer id,
        @RequestBody SendGoodsAddressDO newObj) {
        newObj.setId(id);
        SendGoodsAddressDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.updateById(oldObj);
        return Response.of(oldObj);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 发货地址表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

}
