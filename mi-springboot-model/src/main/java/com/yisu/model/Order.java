package com.yisu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yisu.common.core.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 订单-实体
 * @author xuyisu
 * @date 2020-11-29 15:48:13
 */
@Data
@TableName("order_info")
@EqualsAndHashCode(callSuper=false)
public class Order extends BaseModel {

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    /**
     * 请鞋上代码注释
     */
    @ApiModelProperty(value = "请鞋上代码注释")
    private BigDecimal payment;
    /**
     * 支付类型
     */
    @ApiModelProperty(value = "支付类型")
    private Integer paymentType;
    /**
     * 支付类型描述
     */
    @ApiModelProperty(value = "支付类型描述")
    private String paymentTypeDesc;
    /**
     * 请鞋上代码注释
     */
    @ApiModelProperty(value = "请鞋上代码注释")
    private BigDecimal postage;
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
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private Date paymentTime;
    /**
     * 地址id
     */
    @ApiModelProperty(value = "地址id")
    private Long addressId;
    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人")
    private String receiveName;
    /**
     * 联系号码
     */
    @ApiModelProperty(value = "联系号码")
    private String receivePhone;
    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private String province;
    /**
     * 城市
     */
    @ApiModelProperty(value = "城市")
    private String city;
    /**
     * 区
     */
    @ApiModelProperty(value = "区")
    private String area;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String street;
    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编")
    private String postalCode;
    /**
     * 购买人id
     */
    @ApiModelProperty(value = "购买人id")
    private Long userId;
}