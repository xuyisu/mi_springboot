package com.yisu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.mapper.OrderStatusRecordMapper;
import com.yisu.model.OrderStatusRecord;
import com.yisu.service.OrderStatusRecordService;
import org.springframework.stereotype.Service;

/**
 * @description 订单记录-业务实现
 * @author xuyisu
 * @date 2020-11-29
 */
@Service
public class OrderStatusRecordServiceImpl extends ServiceImpl<OrderStatusRecordMapper, OrderStatusRecord> implements OrderStatusRecordService {
}
