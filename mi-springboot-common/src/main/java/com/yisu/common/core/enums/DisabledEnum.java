package com.yisu.common.core.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @description 启用禁用枚举
 * @author xuyisu
 * @date 2019/2/1
 */
public enum DisabledEnum implements IEnum<Integer> {

    DISABLED(1, "禁用"),
    ENABLE(0, "启用");

    private Integer value;
    private String desc;

    DisabledEnum(int value, String desc) {
        this.value=value;
        this.desc=desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
