package com.yisu.model;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yisu.common.core.model.BaseModel;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 订单明细-实体
 * @author xuyisu
 * @date 2020-11-29 15:51:43
 */
@Data
@TableName("order_detail")
@EqualsAndHashCode(callSuper=false)
public class OrderDetail extends BaseModel {

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    /**
     * 订单明细编号
     */
    @ApiModelProperty(value = "订单明细编号")
    private String orderDetailNo;
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
     * 活动图片地址
     */
    @ApiModelProperty(value = "活动图片地址")
    private String activityMainImage;
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
     * 商品图片地址
     */
    @ApiModelProperty(value = "商品图片地址")
    private String productMainImage;
    /**
     * 请鞋上代码注释
     */
    @ApiModelProperty(value = "请鞋上代码注释")
    private BigDecimal currentUnitPrice;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer quantity;
    /**
     * 请鞋上代码注释
     */
    @ApiModelProperty(value = "请鞋上代码注释")
    private BigDecimal totalPrice;
    /**
     * 购买人id
     */
    @ApiModelProperty(value = "购买人id")
    private Long userId;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private Integer status;
    /**
     * 状态描述
     */
    @ApiModelProperty(value = "状态描述")
    private String statusDesc;
    /**
     * 取消时间
     */
    @ApiModelProperty(value = "取消时间")
    private Date cancelTime;
    /**
     * 取消原因
     */
    @ApiModelProperty(value = "取消原因")
    private Integer cancelReason;
    /**
     * 发货时间
     */
    @ApiModelProperty(value = "发货时间")
    private Date sendTime;
    /**
     * 签收时间
     */
    @ApiModelProperty(value = "签收时间")
    private Date receiveTime;
}