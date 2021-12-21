package com.yisu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.mapper.ProductDetailMapper;
import com.yisu.model.ProductDetail;
import com.yisu.service.ProductDetailService;
import org.springframework.stereotype.Service;

/**
 * @description 商品明细-业务实现
 * @author xuyisu
 * @date 2020-11-29
 */
@Service
public class ProductDetailServiceImpl extends ServiceImpl<ProductDetailMapper, ProductDetail> implements ProductDetailService {
}
