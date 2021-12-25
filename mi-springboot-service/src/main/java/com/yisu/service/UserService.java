package com.yisu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yisu.common.core.result.FwResult;
import com.yisu.model.User;
import com.yisu.vo.LoginVo;

import java.util.Map;

/**
 * @description 用户信息-业务接口
 * @author xuyisu
 * @date 2020-11-29
 */
public interface UserService extends IService<User> {

    FwResult<User> register(User user);

    FwResult<Map<String, Object>> login(LoginVo loginVo);

    FwResult<User> logout(String token);
}
