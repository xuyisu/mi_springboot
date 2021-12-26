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


    /**
     * 分页查询订单
     * @param page
     * @return
     */
    FwResult<IPage<OrderVo>> pages(Page page);

    /**
     * 创建订单
     * @param address
     * @return
     */
    FwResult<String> create(UserAddress address);

    /**
     * 查询订单明细
     * @param orderNo
     * @return
     */
    FwResult<OrderVo> getOrderDetail(String orderNo);

    /**
     * 订单付款
     * @param payReq
     * @return
     */
    FwResult<OrderVo> pay(PayReq payReq);

    /**
     * 取消订单
     * @param orderNo
     * @return
     */
    FwResult<OrderVo> cancel(String orderNo);
}
