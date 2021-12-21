package com.yisu.config.interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.yisu.common.core.config.RedisUtils;
import com.yisu.common.core.constant.FwConstants;
import com.yisu.common.core.exception.TokenCheckException;
import com.yisu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("Authorization");
        if(StrUtil.isBlank(token)){
            throw new TokenCheckException("登录异常");
        }
        String res = redisUtils.get(FwConstants.USER_LOGIN_TOKEN + token);
        if(StringUtils.isEmpty(res)){
            throw new TokenCheckException("铁子,请先登录再购买");
        }
        return true;
    }
}
