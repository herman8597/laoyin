package com.bat.laoyin.web.print;

import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bat.laoyin.api.logistics.dto.KDNOrderOnlineResp;
import com.bat.laoyin.api.makeup.dto.data.DispatchListRespDTO;
import com.bat.laoyin.api.makeup.dto.data.DistributionCodeRespDTO;
import com.bat.laoyin.api.makeup.dto.data.MaterialRequisitionRespDTO;
import com.bat.laoyin.api.print.PrintService;
import com.bat.laoyin.web.base.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author: lim
 * @description: 打印任务资源创建控制器
 * @date: 2021/9/26 19:27
 */
@RestController
@Api(tags = "打印任务资源创建控制器", value = "PrintTaskController")
@RequestMapping("/printTask")
public class PrintTaskController {

    @Resource
    private PrintService service;

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.io.tmpdir") + File.separator);
    }

    @GetMapping("/materialRequisition/{code}")
    @ApiOperation(value = "打印领料单", notes = "")
    @ApiImplicitParam(name = "code", value = "拼版任务编码")
    public Response<MaterialRequisitionRespDTO> getMaterialRequisition(@PathVariable String code)
        throws ExecutionException, InterruptedException {
        MaterialRequisitionRespDTO materialRequisition = service.getMaterialRequisition(code).get();
        return Response.of(materialRequisition);
    }

    @GetMapping("/distributionCodeLabel/{code}")
    @ApiOperation(value = "打印配货标签", notes = "")
    @ApiImplicitParam(name = "code", value = "拼版任务编码")
    public Response<DistributionCodeRespDTO> getDistributionCodeLabel(@PathVariable String code)
        throws ExecutionException, InterruptedException {
        DistributionCodeRespDTO distributionCode = service.getDistributionCodeLabel(code).get();
        return Response.of(distributionCode);
    }

    @GetMapping("/dispatchList/{code}")
    @ApiOperation(value = "打印发货单", notes = "")
    @ApiImplicitParam(name = "code", value = "配货标签编码（带-XXX）")
    public Response<DispatchListRespDTO> getDispatchList(@PathVariable String code)
        throws ExecutionException, InterruptedException {
        DispatchListRespDTO dto = service.getDispatchList(code).get();
        return Response.of(dto);
    }

    @GetMapping("/waybill/{code}")
    @ApiOperation(value = "打印运单", notes = "")
    @ApiImplicitParam(name = "code", value = "发货单编码")
    public Response<KDNOrderOnlineResp> getWaybill(@PathVariable String code)
        throws ExecutionException, InterruptedException {
        KDNOrderOnlineResp kdnOrderOnlineResp = service.getWaybill(code).get();
        return Response.of(kdnOrderOnlineResp);
    }
}
