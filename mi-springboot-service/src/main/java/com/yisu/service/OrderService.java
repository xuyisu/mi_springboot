package com.yisu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yisu.common.core.result.FwResult;
import com.yisu.model.Order;
import com.yisu.model.UserAddress;
import com.yisu.request.PayReq;
import com.yisu.vo.OrderVo;

/**
 * @description 订单-业务接口
 * @author xuyisu
 * @date 2020-11-29
 */
public interface OrderService extends IService<Order> {


    FwResult<IPage<OrderVo>> pages(Page page);

    FwResult<String> create(UserAddress address);

    FwResult<OrderVo> getOrderDetail(String orderNo);

    FwResult<OrderVo> pay(PayReq payReq);

    FwResult<OrderVo> cancel(String orderNo);
}
