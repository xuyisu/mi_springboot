package com.yisu.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yisu.common.core.config.RedisUtils;
import com.yisu.common.core.enums.StatusEnum;
import com.yisu.common.core.result.FwResult;
import com.yisu.model.User;
import com.yisu.service.UserService;
import com.yisu.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.yisu.common.core.constant.FwConstants.USER_LOGIN_TOKEN;

/**
 * @description 用户信息-控制层
 * @author xuyisu
 * @date 2020-11-29
 */
@RestController
@Api(value = "用户信息")
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public FwResult<User> register(@RequestBody @Valid User user){

        User mobile=new User();
        mobile.setStatus(StatusEnum.ENABLE.getValue());
        mobile.setPhone(user.getPhone());
        User mobileUser = userService.getOne(Wrappers.query(mobile));
        if(ObjectUtil.isNotNull(mobileUser)){
            return FwResult.failedMsg("当前手机号已存在");
        }
        user.setPassword(SecureUtil.md5(user.getPassword()));
        userService.save(user);
        return FwResult.ok();
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getUser")
    public FwResult<User> getUser(){

        User user = userService.getById(1);
        return FwResult.ok(user);
    }

    @ApiOperation("用户注册")
    @PostMapping("/login")
    public FwResult<Map<String,Object>> login(@RequestBody @Valid LoginVo loginVo){

        User mobile=new User();
        mobile.setStatus(StatusEnum.ENABLE.getValue());
        mobile.setUserName(loginVo.getUserName());
        User mobileUser = userService.getOne(Wrappers.query(mobile));
        if(ObjectUtil.isNotNull(mobileUser)){
            if(mobileUser.getPassword().equals(SecureUtil.md5(loginVo.getPassword()))){
                String key= UUID.randomUUID().toString();
                String userCache = JSONUtil.toJsonStr(mobileUser);
                redisUtils.set(USER_LOGIN_TOKEN +key,userCache,360000);
                log.info("用户登录=>{}",userCache);

                Map<String,Object> map=new HashMap<>();
                map.put("Authorization",key);
                map.put("userInfo",mobileUser);
                return FwResult.ok(map);
            }
        }
        return FwResult.failedMsg("用户名或密码错误");
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public FwResult<User> logout(@RequestHeader(value = "Authorization",required = false) String token){

        if(StrUtil.isNotBlank(token)){
            redisUtils.del(USER_LOGIN_TOKEN+token);
        }
        return FwResult.okMsg("退出成功");
    }
}
