package com.yisu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yisu.common.core.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author xuyisu
 * @description 购物车-实体
 * @date 2020/11/29
 */
@Data
@TableName("cart")
@EqualsAndHashCode(callSuper=false)
public class Cart extends BaseModel {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;
    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Long activityId;
    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称")
    private String activityName;
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long productId;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;
    /**
     * 商品简要描述
     */
    @ApiModelProperty(value = "商品简要描述")
    private String productSubtitle;

    /**
     * 商品图片地址
     */
    @ApiModelProperty(value = "商品图片地址")
    private String productMainImage;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer quantity;
    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal productUnitPrice;

    @ApiModelProperty(value = "总价")
    private BigDecimal productTotalPrice;

    @ApiModelProperty(value = "是否已选择 1 选择 0 未选择")
    private Integer selected;
}
