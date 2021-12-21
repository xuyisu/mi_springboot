package com.yisu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yisu.common.core.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @description 商品-实体
 * @author xuyisu
 * @date 2020-11-29 15:57:38
 */
@Data
@TableName("product")
@EqualsAndHashCode(callSuper=false)
public class Product extends BaseModel {

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long productId;
    /**
     * 品类id
     */
    @ApiModelProperty(value = "品类id")
    private Long categoryId;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String name;
    /**
     * 简要描述
     */
    @ApiModelProperty(value = "简要描述")
    private String subTitle;
    /**
     * 商品图片地址
     */
    @ApiModelProperty(value = "商品图片地址")
    private String mainImage;
    /**
     * 子图片列表
     */
    @ApiModelProperty(value = "子图片列表")
    private String subImages;
    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Long activityId;
    /**
     * 商品状态
     */
    @ApiModelProperty(value = "商品状态")
    private Integer status;
    /**
     * 商品价格
     */
    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;
    /**
     * 库存数
     */
    @ApiModelProperty(value = "库存数")
    private Integer stock;
}
