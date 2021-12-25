package com.yisu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yisu.common.core.result.FwResult;
import com.yisu.model.Cart;
import com.yisu.model.Product;
import com.yisu.request.CartCountChangeReq;
import com.yisu.vo.CartVo;

/**
 * @description 购物车-业务接口
 * @author xuyisu
 * @date 2020-11-29
 */
public interface CartService extends IService<Cart> {

    /**
     * 获取购物车列表
     * @return
     */
    CartVo getCartList();

    FwResult<Integer> add(Product param);

    FwResult<String> updateCount(Long productId, CartCountChangeReq cartCountChangeReq);

    FwResult<String> deleteByProductId(Long productId);

    FwResult<String> selectAll();

    FwResult<String> unSelectAll();

    FwResult<Integer> sum();
}
