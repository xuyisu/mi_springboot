package com.yisu.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yisu.common.core.result.FwResult;
import com.yisu.model.Category;
import com.yisu.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xuyisu
 * @description 列木控制层
 * @date 2020/12/3
 */
@RestController
@Api(value = "类目")
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("商品主信息")
    @GetMapping("/list")
    public FwResult<List<Category>> list(Category category){
        return FwResult.ok(categoryService.list(Wrappers.query(category)));
    }

}
