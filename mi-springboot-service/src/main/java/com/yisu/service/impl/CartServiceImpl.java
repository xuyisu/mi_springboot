package com.yisu.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.mapper.CartMapper;
import com.yisu.model.Cart;
import com.yisu.service.CartService;
import com.yisu.vo.CartVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xuyisu
 * @description 购物车-业务实现
 * @date 2020/11/29
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Override
    public CartVo getCartList() {
        List<Cart> carts = this.list();
        if(CollectionUtil.isEmpty(carts)){
            return null;
        }else{
            CartVo cartVo = CartVo.builder().cartProductList(carts).build();
            int totalQuantity = carts.stream().map(Cart::getQuantity).mapToInt(e -> e).sum();
            BigDecimal totalPrice = carts.stream().map(Cart::getProductTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            cartVo.setCartTotalPrice(totalPrice);
            cartVo.setCartTotalQuantity(totalQuantity);
            AtomicReference<Boolean> selectAll= new AtomicReference<>(Boolean.TRUE);
            carts.stream().map(Cart::getSelected).forEach(e->{
                if(e<1){
                    selectAll.set(Boolean.FALSE);
                }
            });
            cartVo.setSelectedAll(selectAll.get());
            return cartVo;
        }
    }
}
