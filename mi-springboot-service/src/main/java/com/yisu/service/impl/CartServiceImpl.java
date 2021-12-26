package com.yisu.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.common.core.enums.SelectedEnum;
import com.yisu.common.core.enums.StatusEnum;
import com.yisu.common.core.result.FwResult;
import com.yisu.mapper.CartMapper;
import com.yisu.model.Activity;
import com.yisu.model.Cart;
import com.yisu.model.Product;
import com.yisu.request.CartCountChangeReq;
import com.yisu.service.ActivityService;
import com.yisu.service.CartService;
import com.yisu.service.ProductService;
import com.yisu.service.config.AuthUser;
import com.yisu.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xuyisu
 * @description 购物车-业务实现
 * @date 2020/11/29
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    public static final int ONE = 1;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ProductService productService;

    @Override
    public CartVo getCartList() {
        Cart cartParam = new Cart();
        cartParam.setUserId(AuthUser.getUserId());
        List<Cart> carts = this.list(Wrappers.query(cartParam));
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
                if(e< ONE){
                    selectAll.set(Boolean.FALSE);
                }
            });
            cartVo.setSelectedAll(selectAll.get());
            return cartVo;
        }
    }

    @Override
    @Transactional
    public FwResult<Integer> add(Product param) {
        Product productParam = new Product();
        productParam.setProductId(param.getProductId());
        productParam.setStatus(StatusEnum.ENABLE.getValue());
        Product product = productService.getOne(Wrappers.query(productParam));
        if (ObjectUtil.isNull(product)) {
            return FwResult.failedMsg("当前商品已下架或删除");
        }
        //查询商品是否已添加
        Cart cartParam = new Cart();
        cartParam.setProductId(param.getProductId());
        cartParam.setUserId(AuthUser.getUserId());
        Cart cart = this.getOne(Wrappers.query(cartParam));
        if (ObjectUtil.isNull(cart)) {
            cart = new Cart();
            cart.setActivityId(product.getActivityId());
            cart.setUserId(AuthUser.getUserId());
            cart.setCreateUser(AuthUser.getUserId().toString());
            Activity activity = activityService.getActivityByActivityId(product.getActivityId());
            if(ObjectUtil.isNotEmpty(activity)) {
                cart.setActivityName(activity.getName());
            }
            cart.setProductId(param.getProductId());
            cart.setProductSubtitle(product.getSubTitle());
            cart.setProductUnitPrice(product.getPrice());
            cart.setProductMainImage(product.getMainImage());
            cart.setProductName(product.getName());
            cart.setQuantity(ONE);
            BigDecimal quantity = new BigDecimal(String.valueOf(cart.getQuantity()));
            cart.setProductTotalPrice(cart.getProductUnitPrice().multiply(quantity));
            this.save(cart);
        } else {
            cart.setUpdateTime(null);
            cart.setUpdateUser(AuthUser.getUserId().toString());
            cart.setQuantity(cart.getQuantity() + ONE);
            BigDecimal quantity = new BigDecimal(String.valueOf(cart.getQuantity()));
            cart.setProductTotalPrice(product.getPrice().multiply(quantity));
            this.updateById(cart);
        }
        return this.sum();
    }



    @Override
    @Transactional
    public FwResult<String> updateCount(Long productId, CartCountChangeReq cartCountChangeReq) {
        Product productParam = new Product();
        productParam.setProductId(productId);
        productParam.setStatus(StatusEnum.ENABLE.getValue());
        Product product = productService.getOne(Wrappers.query(productParam));
        if (ObjectUtil.isNull(product)) {
            return FwResult.failedMsg("当前商品已下架或删除");
        }
        //查询商品是否已添加
        Cart cartParam = new Cart();
        cartParam.setProductId(productId);
        Cart cart = this.getOne(Wrappers.query(cartParam));
        if(ObjectUtil.isNull(cart)){
            return FwResult.failedMsg("购物车已不存在该商品");
        }
        cart.setUpdateTime(null);
        if(cartCountChangeReq.getType()== ONE){
            cart.setQuantity(cart.getQuantity()+ ONE);
        }else{
            if(cart.getQuantity()<= ONE){
                return FwResult.failedMsg("不能再减了,要减没了");
            }
            cart.setQuantity(cart.getQuantity()- ONE);
        }
        BigDecimal quantity = new BigDecimal(String.valueOf(cart.getQuantity()));
        cart.setProductTotalPrice(product.getPrice().multiply(quantity));
        cart.setSelected(cartCountChangeReq.getSelected());
        cart.setUpdateUser(AuthUser.getUserId().toString());
        cart.setUpdateTime(new Date());
        this.updateById(cart);
        return FwResult.ok();
    }

    @Override
    @Transactional
    public FwResult<String> deleteByProductId(Long productId) {
        //查询商品是否已添加
        Cart cartParam = new Cart();
        cartParam.setProductId(productId);
        Cart cart = this.getOne(Wrappers.query(cartParam));
        if(ObjectUtil.isNull(cart)){
            return FwResult.failedMsg("购物车已不存在该商品");
        }
        this.removeById(cart.getId());
        return FwResult.ok();
    }

    @Override
    @Transactional
    public FwResult<String> selectAll() {
        //查询商品是否已添加
        Cart cartParam = new Cart();
        //获取当前用户
        cartParam.setUserId(AuthUser.getUserId());
        List<Cart> cartList = this.list(Wrappers.query(cartParam));
        if(CollectionUtil.isNotEmpty(cartList)){
            for (Cart cart:cartList) {
                cart.setSelected(SelectedEnum.SELECTED.getValue());
                cart.setUpdateUser(AuthUser.getUserId().toString());
                cart.setUpdateTime(new Date());
            }
        }

        this.updateBatchById(cartList);
        return FwResult.ok();
    }

    @Override
    @Transactional
    public FwResult<String> unSelectAll() {
        //查询商品是否已添加
        Cart cartParam = new Cart();
        //获取当前用户
        cartParam.setUserId(AuthUser.getUserId());
        List<Cart> cartList = this.list(Wrappers.query(cartParam));
        if(CollectionUtil.isNotEmpty(cartList)){
            for (Cart cart:cartList) {
                cart.setSelected(SelectedEnum.UN_SELECTED.getValue());
                cart.setUpdateUser(AuthUser.getUserId().toString());
                cart.setUpdateTime(new Date());
            }
        }
        this.updateBatchById(cartList);
        return FwResult.ok();
    }

    @Override
    public FwResult<Integer> sum() {
        //查询商品是否已添加
        Cart cartParam = new Cart();
        //获取当前用户
        cartParam.setUserId(AuthUser.getUserId());
        int cartSum = this.count(Wrappers.query(cartParam));

        return FwResult.ok(cartSum);
    }
}
