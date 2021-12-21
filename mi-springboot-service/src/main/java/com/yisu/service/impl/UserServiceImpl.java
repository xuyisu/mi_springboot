package com.yisu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.mapper.UserMapper;
import com.yisu.model.User;
import com.yisu.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @description 用户信息-业务实现
 * @author xuyisu
 * @date 2020-11-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
