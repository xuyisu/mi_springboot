package com.yisu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yisu.common.core.result.FwResult;
import com.yisu.model.UserAddress;

/**
 * @description 用户地址-业务接口
 * @author xuyisu
 * @date 2020-11-29
 */
public interface UserAddressService extends IService<UserAddress> {


    FwResult<Boolean> add(UserAddress address);

    FwResult<UserAddress> getAddressDetail(Long addressId);

    FwResult<Boolean> delete(Long addressId);

    FwResult<Boolean> updateAddress(Long addressId,UserAddress address);
}
