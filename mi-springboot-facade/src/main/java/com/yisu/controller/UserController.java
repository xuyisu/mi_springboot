package com.yisu.controller;

import com.yisu.common.core.result.FwResult;
import com.yisu.model.User;
import com.yisu.service.UserService;
import com.yisu.service.config.AuthUser;
import com.yisu.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


/**
 * @description 用户信息-控制层
 * @author xuyisu
 * @date 2020-11-29
 */
@RestController
@Api(value = "用户信息")
@RequestMapping("/api/user")
@Slf4j
@Validated
public class UserController {


    @Autowired
    private UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public FwResult<User> register(@RequestBody @Valid User user){

       return userService.register(user);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getUser")
    public FwResult<User> getUser(){
        return FwResult.ok(AuthUser.get());
    }

    @ApiOperation("用户注册")
    @PostMapping("/login")
    public FwResult<Map<String,Object>> login(@RequestBody @Valid LoginVo loginVo){

        return userService.login(loginVo);
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public FwResult<User> logout(@RequestHeader(value = "Authorization",required = false) String token){

        return userService.logout(token);
    }
}
