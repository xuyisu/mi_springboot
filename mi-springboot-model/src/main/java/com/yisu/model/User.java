package com.yisu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yisu.common.core.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @description 用户信息-实体
 * @author xuyisu
 * @date 2020-11-29 16:03:17
 */
@Data
@TableName("user")
@EqualsAndHashCode(callSuper=false)
public class User extends BaseModel {

    /**
     * 启用标志
     */
    @ApiModelProperty(value = "状态 1启用 0 禁用")
    @JsonIgnore
    private Integer status;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名称不能为空")
    private String userName;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    @Size(max = 11,message = "长度不能超出11位")
    private String phone;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @JsonIgnore
    private String password;
}
