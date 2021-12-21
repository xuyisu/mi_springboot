package com.yisu.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PayReq {

    @NotBlank(message = "订单编号不能为空")
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @NotBlank(message = "支付方式不能为空")
    @ApiModelProperty(value = "支付方式 1微信支付  2支付宝支付")
    private Integer payTool;
}
