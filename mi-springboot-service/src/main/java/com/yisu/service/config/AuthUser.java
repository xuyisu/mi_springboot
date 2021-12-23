package com.yisu.service.config;

import com.yisu.common.core.exception.TokenCheckException;
import com.yisu.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthUser {
    private static final ThreadLocal<User> threadLocal = new ThreadLocal() ;

    public static User get() {
        return threadLocal.get();
    }

    public static void set(User value) {
        threadLocal.set(value);
    }


    public static void remove() {
        threadLocal.remove();
    }


    public static Long getUserId() {
        try {
            return threadLocal.get().getId();
        } catch (Exception e) {
            log.error("用户信息获取失败",e);
            throw new TokenCheckException("铁子,请先登录再购买");
        }
    }

}
