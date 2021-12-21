package com.yisu.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CartCountChangeReq {

    @NotBlank(message = "请求数量不能为空")
    @ApiModelProperty(value = "请求数量")
    private Integer quantity;

    @NotBlank(message = "是否选中不能为空")
    @ApiModelProperty(value = "是否选中")
    private Integer selected;

    @NotBlank(message = "类型不能为空")
    @ApiModelProperty(value = "类型 1增加 0减少")
    private Integer type;
}
