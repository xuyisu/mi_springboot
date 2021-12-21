package com.yisu.common.core.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @description 启用禁用枚举
 * @author xuyisu
 * @date 2019/2/1
 */
public enum SelectedEnum implements IEnum<Integer> {

    UN_SELECTED(0, "未选择"),
    SELECTED(1, "已选择");

    private Integer value;
    private String desc;

    SelectedEnum(int value, String desc) {
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
