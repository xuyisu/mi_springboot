package com.yisu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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



}
