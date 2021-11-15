package com.bat.laoyin.web.third;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import com.bat.laoyin.api.order.OrderInfoService;
import com.bat.laoyin.api.third.dto.AppInfo;
import com.bat.laoyin.api.third.dto.OrderInfoCreateCmd;
import com.bat.laoyin.api.third.dto.ThirdOrderCreateCmd;
import com.bat.laoyin.web.base.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/9 19:39
 */
@Api(tags = "第三方接口", value = "ThirdController")
@RestController
@RequestMapping("/v1/third")
public class ThirdController extends ThirdBaseController {

    @Resource
    private OrderInfoService orderInfoService;

    @PostMapping("/createOrder")
    @ApiOperation(value = "第三方 创建订单", notes = "")
    public Response createOrder(HttpServletRequest request, AppInfo authInfo, @RequestBody ThirdOrderCreateCmd cmd) {
        cmd.getOrderInfo().setCustomerId(getCustomerId());
        cmd.getOrderInfo().setCustomerName(getCustomerName());
        orderInfoService.mySave(cmd);
        return Response.buildSuccess();
    }

    @PutMapping("/cancelOrder")
    @ApiOperation(value = "第三方 取消订单", notes = "")
    public Response<OrderInfoCreateCmd> cancelOrder(@RequestBody OrderInfoCreateCmd aDo) {
        return Response.buildSuccess();
    }

    @PutMapping("/listDistributionMode")
    @ApiOperation(value = "第三方 获取配送方式列表", notes = "")
    public Response<OrderInfoCreateCmd> listDistributionMode(@RequestBody OrderInfoCreateCmd aDo) {
        return Response.buildSuccess();
    }

}
