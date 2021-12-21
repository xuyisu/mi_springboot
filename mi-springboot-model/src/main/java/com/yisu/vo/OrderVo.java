package com.yisu.vo;

import com.yisu.model.Order;
import com.yisu.model.OrderDetail;
import lombok.Data;

import java.util.List;

/**
 * @author xuyisu
 * @description 订单信息
 * @date 2020/12/9
 */
@Data
public class OrderVo extends Order {

    /**
     * 订单详情列表
     */
    private List<OrderDetailVo> details;
}
