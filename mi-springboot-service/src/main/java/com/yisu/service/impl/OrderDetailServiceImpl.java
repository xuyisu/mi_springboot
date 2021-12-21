package com.yisu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.mapper.OrderDetailMapper;
import com.yisu.model.OrderDetail;
import com.yisu.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @description 订单明细-业务实现
 * @author xuyisu
 * @date 2020-11-29
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
