package com.yisu.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yisu.common.core.enums.SelectedEnum;
import com.yisu.common.core.enums.StatusEnum;
import com.yisu.common.core.result.FwResult;
import com.yisu.config.AuthUser;
import com.yisu.model.Activity;
import com.yisu.model.Cart;
import com.yisu.model.Category;
import com.yisu.model.Product;
import com.yisu.request.CartCountChangeReq;
import com.yisu.service.ActivityService;
import com.yisu.service.CartService;
import com.yisu.service.ProductService;
import com.yisu.vo.CartVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xuyisu
 * @description 购物车-控制层
 * @date 2020/11/29
 */
@RestController
@Api(value = "购物车")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ActivityService activityService;

    @ApiOperation("购物车分页查询")
    @GetMapping("/list")
    public FwResult<CartVo> list(){
        return FwResult.ok(cartService.getCartList());
    }


    @ApiOperation("添加购物车")
    @PostMapping("/add")
    public FwResult<Integer> add(@RequestBody Product param) {

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
        Cart cart = cartService.getOne(Wrappers.query(cartParam));
        if (ObjectUtil.isNull(cart)) {
            cart = new Cart();
            cart.setActivityId(product.getActivityId());
            cart.setUserId(AuthUser.getUserId());
            cart.setCreateUser(AuthUser.getUserId().toString());
            Activity activityParam = new Activity();
            activityParam.setActivityId(product.getActivityId());
            activityParam.setStatus(StatusEnum.ENABLE.getValue());
            QueryWrapper<Activity> queryActivity = Wrappers.query(activityParam);
            String date = DateUtil.today();
            queryActivity.lt("start_time", date);
            queryActivity.gt("end_time", date);
            Activity activity = activityService.getOne(queryActivity);
            if(ObjectUtil.isNotEmpty(activity)) {
                cart.setActivityName(activity.getName());
            }
            cart.setProductId(param.getProductId());
            cart.setProductSubtitle(product.getSubTitle());
            cart.setProductUnitPrice(product.getPrice());
            cart.setProductMainImage(product.getMainImage());
            cart.setProductName(product.getName());
            cart.setQuantity(1);
            BigDecimal quantity = new BigDecimal(String.valueOf(cart.getQuantity()));
            cart.setProductTotalPrice(cart.getProductUnitPrice().multiply(quantity));
            cartService.save(cart);
        } else {
            cart.setUpdateTime(null);
            cart.setUpdateUser(AuthUser.getUserId().toString());
            cart.setQuantity(cart.getQuantity() + 1);
            BigDecimal quantity = new BigDecimal(String.valueOf(cart.getQuantity()));
            cart.setProductTotalPrice(product.getPrice().multiply(quantity));
            cartService.updateById(cart);
        }
        return this.sum();
    }


    @ApiOperation("改变数量")
    @PutMapping("/{productId}")
    public FwResult<String> update(@PathVariable("productId") Long productId,@RequestBody CartCountChangeReq cartCountChangeReq) {

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
        Cart cart = cartService.getOne(Wrappers.query(cartParam));
        if(ObjectUtil.isNull(cart)){
            return FwResult.failedMsg("购物车已不存在该商品");
        }
        cart.setUpdateTime(null);
        if(cartCountChangeReq.getType()==1){
            cart.setQuantity(cart.getQuantity()+1);
        }else{
            if(cart.getQuantity()<=1){
                return FwResult.failedMsg("不能再减了,要减没了");
            }
            cart.setQuantity(cart.getQuantity()-1);
        }
        BigDecimal quantity = new BigDecimal(String.valueOf(cart.getQuantity()));
        cart.setProductTotalPrice(product.getPrice().multiply(quantity));
        cart.setSelected(cartCountChangeReq.getSelected());
        cart.setUpdateUser(AuthUser.getUserId().toString());
        cart.setUpdateTime(new Date());
        cartService.updateById(cart);
        return FwResult.ok();
    }


    @ApiOperation("删除")
    @DeleteMapping("/{productId}")
    public FwResult<String> delete(@PathVariable("productId") Long productId) {

        //查询商品是否已添加
        Cart cartParam = new Cart();
        cartParam.setProductId(productId);
        Cart cart = cartService.getOne(Wrappers.query(cartParam));
        if(ObjectUtil.isNull(cart)){
            return FwResult.failedMsg("购物车已不存在该商品");
        }
        cartService.removeById(cart.getId());
        return FwResult.ok();
    }

    @ApiOperation("全选")
    @PutMapping("/selectAll")
    public FwResult<String> selectAll() {

        //查询商品是否已添加
        Cart cartParam = new Cart();
        //获取当前用户
        cartParam.setUserId(AuthUser.getUserId());
        List<Cart> cartList = cartService.list(Wrappers.query(cartParam));
        if(CollectionUtil.isNotEmpty(cartList)){
            for (Cart cart:cartList) {
                cart.setSelected(SelectedEnum.SELECTED.getValue());
                cart.setUpdateUser(AuthUser.getUserId().toString());
                cart.setUpdateTime(new Date());
            }
        }

        cartService.updateBatchById(cartList);
        return FwResult.ok();
    }

    @ApiOperation("不全选")
    @PutMapping("/unSelectAll")
    public FwResult<String> unSelectAll() {

        //查询商品是否已添加
        Cart cartParam = new Cart();
        //获取当前用户
        cartParam.setUserId(AuthUser.getUserId());
        List<Cart> cartList = cartService.list(Wrappers.query(cartParam));
        if(CollectionUtil.isNotEmpty(cartList)){
            for (Cart cart:cartList) {
                cart.setSelected(SelectedEnum.UN_SELECTED.getValue());
                cart.setUpdateUser(AuthUser.getUserId().toString());
                cart.setUpdateTime(new Date());
            }
        }

        cartService.updateBatchById(cartList);
        return FwResult.ok();
    }

    @ApiOperation("获取购物车数量")
    @GetMapping("/sum")
    public FwResult<Integer> sum() {

        //查询商品是否已添加
        Cart cartParam = new Cart();
        //获取当前用户
        cartParam.setUserId(0L);
        int cartSum = cartService.count(Wrappers.query(cartParam));

        return FwResult.ok(cartSum);
    }



}
