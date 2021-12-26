package com.yisu.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.common.core.result.FwResult;
import com.yisu.mapper.UserAddressMapper;
import com.yisu.model.UserAddress;
import com.yisu.service.UserAddressService;
import com.yisu.service.config.AuthUser;
import org.springframework.stereotype.Service;

/**
 * @description 用户地址-业务实现
 * @author xuyisu
 * @date 2020-11-29
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

    @Override
    public FwResult<Boolean> add(UserAddress address) {
        address.setAddressId(IdUtil.getSnowflake(1L,1L).nextId());
        address.setCreateUser(AuthUser.getUserId().toString());
        address.setUpdateUser(AuthUser.getUserId().toString());
        this.save(address);
        return FwResult.ok();
    }

    @Override
    public FwResult<UserAddress> getAddressDetail(Long addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setAddressId(addressId);
        UserAddress addressDetail = this.getOne(Wrappers.query(userAddress));
        if (ObjectUtil.isNull(addressDetail)) {
            return FwResult.failedMsg("当前地址不存在，请重新添加");
        }
        return FwResult.ok(addressDetail);
    }

    @Override
    public FwResult<Boolean> delete(Long addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setAddressId(addressId);
        UserAddress addressDetail = this.getOne(Wrappers.query(userAddress));
        if (ObjectUtil.isNull(addressDetail)) {
            return FwResult.failedMsg("当前地址不存在，请重新添加");
        }
        return FwResult.ok(this.removeById(addressDetail.getId()));
    }

    @Override
    public FwResult<Boolean> updateAddress(Long addressId,UserAddress address) {
        UserAddress userAddress = new UserAddress();
        userAddress.setAddressId(addressId);
        UserAddress addressDetail = this.getOne(Wrappers.query(userAddress));
        if (ObjectUtil.isNull(addressDetail)) {
            return FwResult.failedMsg("当前地址不存在，请重新添加");
        }
        address.setUpdateUser(AuthUser.getUserId().toString());
        address.setId(addressDetail.getId());
        boolean update = this.updateById(address);
        if(update) {
            return FwResult.ok();
        }
        return FwResult.failedMsg("更新地址失败");
    }
}
