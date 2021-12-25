package com.yisu.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.common.core.enums.OrderStatusEnum;
import com.yisu.common.core.enums.PayTypeEnum;
import com.yisu.common.core.enums.SelectedEnum;
import com.yisu.common.core.enums.StatusEnum;
import com.yisu.common.core.result.FwResult;
import com.yisu.mapper.OrderMapper;
import com.yisu.model.*;
import com.yisu.request.PayReq;
import com.yisu.service.*;
import com.yisu.service.config.AuthUser;
import com.yisu.vo.OrderDetailVo;
import com.yisu.vo.OrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description 订单-业务实现
 * @author xuyisu
 * @date 2020-11-29
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ActivityService activityService;

    @Override
    public FwResult<IPage<OrderVo>> pages(Page page) {
        Order orderParam = new Order();
        orderParam.setUserId(AuthUser.getUserId());
        QueryWrapper<Order> queryWrapper = Wrappers.query(orderParam).orderByDesc("update_time");
        IPage data = this.page(page,queryWrapper);
        List<Order> records = data.getRecords();
        List<OrderVo> orderVoList=new ArrayList<>();
        if(CollectionUtil.isNotEmpty(records)){
            for (Order order:records ) {
                OrderVo orderVo=new OrderVo();
                BeanUtils.copyProperties(order,orderVo);
                //查询订单明细
                OrderDetail orderDetail=new OrderDetail();
                orderDetail.setOrderNo(order.getOrderNo());
                List<OrderDetail> orderDetails = orderDetailService.list(Wrappers.query(orderDetail));
                if(CollectionUtil.isNotEmpty(orderDetails)){
                    List<OrderDetailVo> orderDetailVoList=new ArrayList<>();
                    for (OrderDetail orderDetail1:orderDetails) {
                        OrderDetailVo orderDetailVo=new OrderDetailVo();
                        BeanUtils.copyProperties(orderDetail1,orderDetailVo);
                        orderDetailVoList.add(orderDetailVo);
                    }
                    if(CollectionUtil.isNotEmpty(orderDetailVoList)) {
                        orderVo.setDetails(orderDetailVoList);
                    }
                }
                orderVoList.add(orderVo);
            }
            if(CollectionUtil.isNotEmpty(orderVoList)){
                data.setRecords(orderVoList);
                return FwResult.ok(data);
            }

        }
        return FwResult.ok();
    }

    @Override
    @Transactional
    public FwResult<String> create(UserAddress address) {
        //获取用户地址
        UserAddress addressParam = new UserAddress();
        addressParam.setAddressId(address.getAddressId());

        UserAddress userAddress = userAddressService.getOne(Wrappers.query(addressParam));
        if (ObjectUtil.isEmpty(userAddress)) {
            return FwResult.failedMsg("当前地址已不存在，请重新添加地址");
        }
        //从购物车获取商品
        Cart cartParam = new Cart();
        cartParam.setUserId(AuthUser.getUserId());
        cartParam.setSelected(SelectedEnum.SELECTED.getValue());
        List<Cart> cartList = cartService.list(Wrappers.query(cartParam));
        String orderNo = String.valueOf(System.currentTimeMillis() / 1000); //真实需要自己改造
        BigDecimal totalOrderPrice = new BigDecimal(0);
        if (CollectionUtil.isEmpty(cartList)) {
            return FwResult.failedMsg("恭喜您的购物车已经被清空了，再加一车吧");
        }

        List<OrderDetail> orderDetailList = new ArrayList<>();
        //写订单明细表
        for (Cart cart : cartList) {
            //查询商品
            Product productParam = new Product();
            productParam.setProductId(cart.getProductId());
            productParam.setStatus(StatusEnum.ENABLE.getValue());
            QueryWrapper<Product> queryProduct = Wrappers.query(productParam);
            queryProduct.gt("stock", 0);
            Product product = productService.getOne(queryProduct);
            if (ObjectUtil.isEmpty(product)) {
                return FwResult.failedMsg("商品:" + cart.getProductName() + " 已售尽,请选择其它产品");
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setCurrentUnitPrice(product.getPrice());
            //获取活动信息
            Activity activityParam = new Activity();
            activityParam.setActivityId(product.getActivityId());
            activityParam.setStatus(StatusEnum.ENABLE.getValue());
            QueryWrapper<Activity> queryActivity = Wrappers.query(activityParam);
            String date = DateUtil.today();
            queryActivity.lt("start_time", date);
            queryActivity.gt("end_time", date);
            Activity activity = activityService.getOne(queryActivity);
            if (ObjectUtil.isNotEmpty(activity)) {
                orderDetail.setActivityId(activity.getActivityId());
                orderDetail.setActivityName(activity.getName());
                orderDetail.setActivityMainImage(activity.getMainImage());
            }
            orderDetail.setOrderDetailNo(String.valueOf(System.currentTimeMillis()));
            orderDetail.setOrderNo(orderNo);
            orderDetail.setProductId(product.getProductId());
            orderDetail.setProductMainImage(product.getMainImage());
            orderDetail.setProductName(product.getName());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setStatus(OrderStatusEnum.UN_PAY.getValue());
            orderDetail.setStatusDesc(OrderStatusEnum.UN_PAY.getDesc());
            orderDetail.setTotalPrice(cart.getProductTotalPrice());
            orderDetail.setUserId(AuthUser.getUserId());
            orderDetail.setCreateUser(AuthUser.getUserId().toString());
            orderDetailList.add(orderDetail);
            totalOrderPrice = totalOrderPrice.add(orderDetail.getTotalPrice());
            //删除购物车数据
            this.removeById(cart.getId());
        }
        //写入订单主表
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setAddressId(userAddress.getAddressId());
        order.setArea(userAddress.getArea());
        order.setCity(userAddress.getCity());
        order.setPayment(totalOrderPrice);
        order.setPaymentType(PayTypeEnum.ON_LINE.getValue());
        order.setPaymentTypeDesc(PayTypeEnum.ON_LINE.getDesc());
        order.setPostalCode(userAddress.getPostalCode());
        order.setProvince(userAddress.getProvince());
        order.setReceiveName(userAddress.getReceiveName());
        order.setReceivePhone(userAddress.getReceivePhone());
        order.setStreet(userAddress.getStreet());
        order.setStatus(OrderStatusEnum.UN_PAY.getValue());
        order.setStatusDesc(OrderStatusEnum.UN_PAY.getDesc());
        order.setCreateUser(AuthUser.getUserId().toString());
        order.setUserId(AuthUser.getUserId());
        if (CollectionUtil.isNotEmpty(orderDetailList)) {
            this.save(order);
            orderDetailService.saveBatch(orderDetailList);
        }


        return FwResult.ok(orderNo);
    }

    @Override
    public FwResult<OrderVo> getOrderDetail(String orderNo) {
        Order orderParam = new Order();
        orderParam.setOrderNo(orderNo);
        Order order = this.getOne(Wrappers.query(orderParam));
        if (ObjectUtil.isNotEmpty(order)) {
            if(!order.getUserId().equals(AuthUser.getUserId())){
                return FwResult.failedMsg("您无权查询他人订单");
            }
            OrderVo orderVo=new OrderVo();
            BeanUtils.copyProperties(order,orderVo);
            //查询订单明细
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setOrderNo(order.getOrderNo());
            List<OrderDetail> orderDetails = orderDetailService.list(Wrappers.query(orderDetail));
            if(CollectionUtil.isNotEmpty(orderDetails)){
                List<OrderDetailVo> orderDetailVoList=new ArrayList<>();
                for (OrderDetail orderDetail1:orderDetails) {
                    OrderDetailVo orderDetailVo=new OrderDetailVo();
                    BeanUtils.copyProperties(orderDetail1,orderDetailVo);
                    orderDetailVoList.add(orderDetailVo);
                }
                if(CollectionUtil.isNotEmpty(orderDetailVoList)) {
                    orderVo.setDetails(orderDetailVoList);
                }
            }
            return FwResult.ok(orderVo);
        }
        return FwResult.ok();
    }

    @Override
    public FwResult<OrderVo> pay(PayReq payReq) {
        Order orderParam = new Order();
        orderParam.setOrderNo(payReq.getOrderNo());
        Order order = this.getOne(Wrappers.query(orderParam));
        if (ObjectUtil.isNotEmpty(order)) {
            if(!order.getUserId().equals(AuthUser.getUserId())){
                return FwResult.failedMsg("您无权查询他人订单");
            }
            if(!order.getStatus().equals(OrderStatusEnum.UN_PAY.getValue())){
                return FwResult.failedMsg("您没有待支付的订单");
            }
            orderParam.setOrderNo(null);
            orderParam.setId(order.getId());
            orderParam.setPaymentTime(new Date());
            orderParam.setStatus(OrderStatusEnum.PAY.getValue());
            orderParam.setStatusDesc(OrderStatusEnum.PAY.getDesc());
            this.updateById(orderParam);

            //订单明细同步更新
            OrderDetail orderDetailParam=new OrderDetail();
            orderDetailParam.setOrderNo(payReq.getOrderNo());
            List<OrderDetail> orderDetailList = orderDetailService.list(Wrappers.query(orderDetailParam));
            if(CollectionUtil.isNotEmpty(orderDetailList)){
                for (OrderDetail orderDetail :orderDetailList) {
                    OrderDetail orderDetailUpdate=new OrderDetail();
                    orderDetailUpdate.setStatus(OrderStatusEnum.PAY.getValue());
                    orderDetailUpdate.setStatusDesc(OrderStatusEnum.PAY.getDesc());
                    orderDetailUpdate.setId(orderDetail.getId());
                    orderDetailService.updateById(orderDetailUpdate);
                }
            }
        }
        return FwResult.ok();
    }

    @Override
    public FwResult<OrderVo> cancel(String orderNo) {
        Order orderParam = new Order();
        orderParam.setOrderNo(orderNo);
        Order order = this.getOne(Wrappers.query(orderParam));
        if (ObjectUtil.isNotEmpty(order)) {
            if(!order.getUserId().equals(AuthUser.getUserId())){
                return FwResult.failedMsg("您无权查询他人订单");
            }
            if(!order.getStatus().equals(OrderStatusEnum.UN_PAY.getValue())){
                return FwResult.failedMsg("订单"+order.getStatusDesc()+",无法取消");
            }
            orderParam.setOrderNo(null);
            orderParam.setId(order.getId());
            orderParam.setUpdateUser(AuthUser.getUserId().toString());
            orderParam.setStatus(OrderStatusEnum.CANCEL.getValue());
            orderParam.setStatusDesc(OrderStatusEnum.CANCEL.getDesc());
            this.updateById(orderParam);

            //订单明细取消
            OrderDetail orderDetailParam=new OrderDetail();
            orderDetailParam.setOrderNo(orderNo);
            List<OrderDetail> orderDetailList = orderDetailService.list(Wrappers.query(orderDetailParam));
            if(CollectionUtil.isNotEmpty(orderDetailList)){
                for (OrderDetail orderDetail :orderDetailList) {
                    OrderDetail orderDetailUpdate=new OrderDetail();
                    orderDetailUpdate.setStatus(OrderStatusEnum.CANCEL.getValue());
                    orderDetailUpdate.setStatusDesc(OrderStatusEnum.CANCEL.getDesc());
                    orderDetailUpdate.setUpdateUser(AuthUser.getUserId().toString());
                    orderDetailUpdate.setId(orderDetail.getId());
                    orderDetailService.updateById(orderDetailUpdate);
                }
            }
        }
        return FwResult.ok();
    }
}
