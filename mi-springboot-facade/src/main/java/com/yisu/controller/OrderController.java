package com.yisu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yisu.common.core.result.FwResult;
import com.yisu.model.*;
import com.yisu.request.PayReq;
import com.yisu.service.*;
import com.yisu.vo.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author xuyisu
 * @description 订单-控制层
 * @date 2020-11-29
 */
@RestController
@Api(value = "订单")
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("订单列表")
    @GetMapping("/pages")
    public FwResult<IPage<OrderVo>> pages(Page page){
        return orderService.pages(page);
    }

    @ApiOperation("创建订单")
    @PostMapping("/create")
    public FwResult<String> create(@RequestBody UserAddress address) {
        return orderService.create(address);
    }

    @ApiOperation("订单详情")
    @GetMapping("/{orderNo}")
    public FwResult<OrderVo> getOrderDetail(@PathVariable String orderNo) {
        return orderService.getOrderDetail(orderNo);
    }

    @ApiOperation("订单付款")
    @PostMapping("/pay")
    public FwResult<OrderVo> pay(@RequestBody PayReq payReq) {
        return orderService.pay(payReq);
    }

    @ApiOperation("取消订单")
    @PutMapping("/{orderNo}")
    public FwResult<OrderVo> cancel(@PathVariable String orderNo) {
        return orderService.cancel(orderNo);
    }
}
