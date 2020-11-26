package com.yisu.common.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description 分页传参
 * @author xuyisu
 * @date 2019-10-11
 */
@Data
@AllArgsConstructor
public class PageResult {
    //页号
    private long current;
    //总页数
    private long totalPage;
    //总条数 返回用
    private long total;
}
