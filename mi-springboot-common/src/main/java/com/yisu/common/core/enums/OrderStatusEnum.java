package com.yisu.common.core.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @description 订单状态
 * @author xuyisu
 * @date 2019/2/1
 */
public enum OrderStatusEnum implements IEnum<Integer> {

    CANCEL(0, "已取消"),
    UN_PAY(10, "未付款"),
    PAY(20, "已付款"),
    SEND(40, "已发货"),
    SUCESS(50, "交易成功");

    private Integer value;
    private String desc;

    OrderStatusEnum(int value, String desc) {
        this.value=value;
        this.desc=desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }
}
