package com.yisu.init;

import lombok.Data;

import java.util.List;

/**
 * @author xuyisu
 * @description 根目录
 * @date 2020/11/29
 */
@Data
public class ProductRoot {
    private String name;

    private ProductAction action;

    private List<ProductList> list ;

}
