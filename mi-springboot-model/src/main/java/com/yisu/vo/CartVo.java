package com.yisu.vo;

import com.yisu.model.Cart;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CartVo {
    @ApiModelProperty(value = "购物车总价")
    private BigDecimal cartTotalPrice;

    @ApiModelProperty(value = "购物车总数量")
    private int cartTotalQuantity;

    @ApiModelProperty(value = "是否全选")
    private boolean selectedAll;

    @ApiModelProperty(value = "购物车明细")
    private List<Cart> cartProductList;
}
