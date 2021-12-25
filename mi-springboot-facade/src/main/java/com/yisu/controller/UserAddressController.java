package com.yisu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yisu.common.core.result.FwResult;
import com.yisu.model.UserAddress;
import com.yisu.service.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xuyisu
 * @description 用户地址-控制层
 * @date 2020-11-29
 */
@RestController
@Api(value = "用户地址")
@RequestMapping("/address")
@Validated
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;


    @ApiOperation("地址列表")
    @GetMapping("/pages")
    public FwResult<IPage> pages(Page page,UserAddress userAddress){
        return FwResult.ok(userAddressService.page(page, Wrappers.query(userAddress)));
    }


    @ApiOperation("地址添加")
    @PostMapping("/add")
    public FwResult<Boolean> addAddress(@RequestBody @Valid UserAddress address) {
        return userAddressService.add(address);
    }

    @ApiOperation("地址详情")
    @GetMapping("/{addressId}")
    public FwResult<UserAddress> getAddressDetail(@PathVariable Long addressId) {
        return userAddressService.getAddressDetail(addressId);
    }

    @ApiOperation("地址删除")
    @DeleteMapping("/{addressId}")
    public FwResult<Boolean> delete(@PathVariable Long addressId) {
        return userAddressService.delete(addressId);
    }

    @ApiOperation("地址更新")
    @PutMapping("/update")
    public FwResult<Boolean> updateAddress(@RequestBody @Valid UserAddress address) {
        return userAddressService.updateAddress(address);
    }

}
