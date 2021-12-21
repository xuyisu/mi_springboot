package com.yisu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.mapper.UserAddressMapper;
import com.yisu.model.UserAddress;
import com.yisu.service.UserAddressService;
import org.springframework.stereotype.Service;

/**
 * @description 用户地址-业务实现
 * @author xuyisu
 * @date 2020-11-29
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

}
