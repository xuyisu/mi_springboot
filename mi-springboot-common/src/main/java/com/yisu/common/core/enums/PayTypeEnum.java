package com.yisu.common.core.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @description 订单状态
 * @author xuyisu
 * @date 2019/2/1
 */
public enum PayTypeEnum implements IEnum<Integer> {

    ON_LINE(1, "在线支付");

    private Integer value;
    private String desc;

    PayTypeEnum(int value, String desc) {
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
