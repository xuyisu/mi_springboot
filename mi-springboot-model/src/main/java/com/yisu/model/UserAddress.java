package com.yisu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yisu.common.core.model.BaseModel;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @description 用户地址-实体
 * @author xuyisu
 * @date 2020-11-29 16:07:59
 */
@Data
@TableName("user_address")
@EqualsAndHashCode(callSuper=false)
public class UserAddress extends BaseModel {

    @ApiModelProperty(value = "地址id")
    private Long addressId;

    /**
     * 默认标志
     */
    @ApiModelProperty(value = "默认标志")
    private Integer defaultFlag;
    /**
     * 收货人
     */
    @NotBlank(message = "收货人不能为空")
    @ApiModelProperty(value = "收货人")
    private String receiveName;
    /**
     * 联系号码
     */
    @NotBlank(message = "联系号码不能为空")
    @ApiModelProperty(value = "联系号码")
    private String receivePhone;
    /**
     * 省份
     */
    @NotBlank(message = "省份不能为空")
    @ApiModelProperty(value = "省份")
    private String province;
    /**
     * 省份编码
     */
    @NotBlank(message = "省份编码不能为空")
    @ApiModelProperty(value = "省份编码")
    private String provinceCode;
    /**
     * 城市
     */
    @NotBlank(message = "城市不能为空")
    @ApiModelProperty(value = "城市")
    private String city;
    /**
     * 城市编码
     */
    @NotBlank(message = "城市编码不能为空")
    @ApiModelProperty(value = "城市编码")
    private String cityCode;
    /**
     * 区
     */
    @NotBlank(message = "区不能为空")
    @ApiModelProperty(value = "区")
    private String area;
    /**
     * 区编码
     */
    @NotBlank(message = "区编码不能为空")
    @ApiModelProperty(value = "区编码")
    private String areaCode;
    /**
     * 详细地址
     */
    @NotBlank(message = "详细地址不能为空")
    @ApiModelProperty(value = "详细地址")
    private String street;
    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编")
    private String postalCode;
    /**
     * 地址标签
     */
    @ApiModelProperty(value = "地址标签")
    private Integer addressLabel;
}
