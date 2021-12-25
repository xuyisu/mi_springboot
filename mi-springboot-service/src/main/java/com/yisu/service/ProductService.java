package com.yisu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yisu.common.core.result.FwResult;
import com.yisu.model.Product;

/**
 * @description 商品-业务接口
 * @author xuyisu
 * @date 2020-12-03
 */
public interface ProductService extends IService<Product> {


    FwResult<Product> getProductDetail(Long productId);
}
