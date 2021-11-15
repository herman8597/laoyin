package com.bat.laoyin.web.makeup;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bat.laoyin.api.makeup.MakeupSettingService;
import com.bat.laoyin.api.makeup.dto.MakeupSettingCmd;
import com.bat.laoyin.api.makeup.dto.MakeupSettingQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupSettingRespDTO;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingDO;
import com.bat.laoyin.web.base.Response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 拼版设置表 前端控制器
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Api(tags = "拼版设置表", value = "MakeupSettingController")
@RestController
@RequestMapping("/v1/makeupSettings")
public class MakeupSettingController {

    @Resource
    private MakeupSettingService service;

    @GetMapping()
    @ApiOperation(value = "获取 拼版设置表 列表数据 所有资源(分页)", notes = "")
    public Response<IPage<MakeupSettingDO>> listAll(MakeupSettingQry qry) {
        IPage<MakeupSettingDO> page = new Page<>(qry.getPage(), qry.getSize());
        return Response.of(service.selectPageVo(page, qry));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id) 获取 拼版设置表 单个或多个资源", notes = "")
    public Response<List<MakeupSettingRespDTO>> getById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.myListByIds(ids));
    }

    @PostMapping()
    @ApiOperation(value = "新增 拼版设置表 单个资源", notes = "")
    public Response create(@RequestBody MakeupSettingCmd aDo) {
        service.mySave(aDo);
        return Response.buildSuccess();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据主键全量更新 拼版设置表 单个资源", notes = "")
    public Response<MakeupSettingDO> updatePutById(@PathVariable Integer id, @RequestBody MakeupSettingCmd newObj) {
        newObj.getMakeupSetting().setId(id);
        service.myUpdateById(newObj);
        return Response.buildSuccess();
    }

    // @PatchMapping("/{id}")
    @ApiOperation(value = "根据主键增量更新 拼版设置表 单个资源", notes = "")
    public Response<MakeupSettingDO> updatePatchById(@PathVariable Integer id, @RequestBody MakeupSettingDO newObj) {
        newObj.setId(id);
        MakeupSettingDO oldObj = service.getById(id);
        BeanUtil.copyProperties(newObj, oldObj, CopyOptions.create().setIgnoreNullValue(true));
        service.updateById(oldObj);
        return Response.of(oldObj);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键(id|id;id;id)删除 拼版设置表 单个或多个资源", notes = "")
    public Response deleteById(@PathVariable String id) {
        List<Integer> ids = Arrays.stream(id.split(";")).map(Integer::valueOf).collect(Collectors.toList());
        return Response.of(service.removeByIds(ids));
    }

}
