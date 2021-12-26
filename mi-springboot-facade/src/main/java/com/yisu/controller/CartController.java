package com.yisu.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yisu.common.core.enums.SelectedEnum;
import com.yisu.common.core.enums.StatusEnum;
import com.yisu.common.core.result.FwResult;
import com.yisu.service.config.AuthUser;
import com.yisu.model.Activity;
import com.yisu.model.Cart;
import com.yisu.model.Product;
import com.yisu.request.CartCountChangeReq;
import com.yisu.service.ActivityService;
import com.yisu.service.CartService;
import com.yisu.service.ProductService;
import com.yisu.vo.CartVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @ApiOperation("购物车分页查询")
    @GetMapping("/list")
    public FwResult<CartVo> list(){
        return FwResult.ok(cartService.getCartList());
    }


    @ApiOperation("添加购物车")
    @PostMapping("/add")
    public FwResult<Integer> add(@RequestBody Product param) {
        return cartService.add(param);
    }


    @ApiOperation("改变数量")
    @PutMapping("/{productId}")
    public FwResult<String> update(@PathVariable("productId") Long productId,@RequestBody CartCountChangeReq cartCountChangeReq) {
        return cartService.updateCount(productId,cartCountChangeReq);
    }


    @ApiOperation("删除")
    @DeleteMapping("/{productId}")
    public FwResult<String> delete(@PathVariable("productId") Long productId) {
        return cartService.deleteByProductId(productId);
    }

    @ApiOperation("全选")
    @PutMapping("/selectAll")
    public FwResult<String> selectAll() {

        return cartService.selectAll();
    }

    @ApiOperation("不全选")
    @PutMapping("/unSelectAll")
    public FwResult<String> unSelectAll() {
        return cartService.unSelectAll();
    }

    @ApiOperation("获取购物车数量")
    @GetMapping("/sum")
    public FwResult<Integer> sum() {
       return cartService.sum();
    }



}
