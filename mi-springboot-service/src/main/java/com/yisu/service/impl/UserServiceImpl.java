package com.yisu.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.common.core.config.RedisUtils;
import com.yisu.common.core.enums.StatusEnum;
import com.yisu.common.core.result.FwResult;
import com.yisu.mapper.UserMapper;
import com.yisu.model.User;
import com.yisu.service.UserService;
import com.yisu.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.yisu.common.core.constant.FwConstants.USER_LOGIN_TOKEN;

/**
 * @description 用户信息-业务实现
 * @author xuyisu
 * @date 2020-11-29
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Transactional
    public FwResult<User> register(User user) {
        User mobile=new User();
        mobile.setStatus(StatusEnum.ENABLE.getValue());
        mobile.setPhone(user.getPhone());
        User mobileUser = this.getOne(Wrappers.query(mobile));
        if(ObjectUtil.isNotNull(mobileUser)){
            return FwResult.failedMsg("当前手机号已存在");
        }
        user.setPassword(SecureUtil.md5(user.getPassword()));
        this.save(user);
        return FwResult.ok();
    }

    @Override
    public FwResult<Map<String, Object>> login(LoginVo loginVo) {
        User mobile=new User();
        mobile.setStatus(StatusEnum.ENABLE.getValue());
        mobile.setUserName(loginVo.getUserName());
        User mobileUser = this.getOne(Wrappers.query(mobile));
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

    @Override
    public FwResult<User> logout(String token) {
        if(StrUtil.isNotBlank(token)){
            redisUtils.del(USER_LOGIN_TOKEN+token);
        }
        return FwResult.okMsg("退出成功");
    }
}
