package com.yisu.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.common.core.result.FwResult;
import com.yisu.mapper.ProductMapper;
import com.yisu.model.Product;
import com.yisu.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @description 商品-业务实现
 * @author xuyisu
 * @date 2020-12-03
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {


    @Override
    public FwResult<Product> getProductDetail(Long productId) {
        Product product =new Product();
        product.setProductId(productId);
        Product productDetail = this.getOne(Wrappers.query(product));
        if(ObjectUtil.isNull(productDetail)){
            return FwResult.failedMsg("该商品已下架或删除");
        }
        return FwResult.ok(productDetail);
    }
}
