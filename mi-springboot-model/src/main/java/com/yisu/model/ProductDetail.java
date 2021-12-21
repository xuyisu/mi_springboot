package com.yisu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yisu.common.core.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description 商品明细-实体
 * @author xuyisu
 * @date 2020-11-29 16:00:25
 */
@Data
@TableName("product_detail")
@EqualsAndHashCode(callSuper=false)
public class ProductDetail extends BaseModel {

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long productId;
    /**
     * 商品详情
     */
    @ApiModelProperty(value = "商品详情")
    private String detail;
    /**
     * 商品参数
     */
    @ApiModelProperty(value = "商品参数")
    private String param;
    /**
     * 轮播图片
     */
    @ApiModelProperty(value = "轮播图片")
    private String rotation;
}
