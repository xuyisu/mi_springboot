package com.yisu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yisu.common.core.result.FwResult;
import com.yisu.model.Product;
import com.yisu.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 商品-控制层
 * @author xuyisu
 * @date 2020-11-29
 */
@RestController
@Api(value = "商品")
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @ApiOperation("商品主信息")
    @GetMapping("/pages")
    public FwResult<IPage> pages(Page page,Product product){
        return FwResult.ok(productService.page(page, Wrappers.query(product)));
    }

    @ApiOperation("商品详情")
    @GetMapping("/{productId}")
    public FwResult<Product> getProductDetail(@PathVariable Long productId){
        return productService.getProductDetail(productId);
    }

}
