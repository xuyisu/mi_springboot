package com.yisu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.mapper.OrderMapper;
import com.yisu.model.Order;
import com.yisu.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @description 订单-业务实现
 * @author xuyisu
 * @date 2020-11-29
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {



}
