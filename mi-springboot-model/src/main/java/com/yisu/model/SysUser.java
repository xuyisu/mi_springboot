package com.yisu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yisu.common.core.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 系统用户表-实体
 * @author xuyisu
 * @date '2019-10-17 19:25:49'.
 */
@Data
@TableName("sys_user")
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUser extends BaseModel {

    /**
     * 职位编码
     */
    @ApiModelProperty(value = "职位编码")
    private String posCode;
    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String avatar;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    /**
     * 部门编码
     */
    @ApiModelProperty(value = "部门编码")
    private String deptCode;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String userPhone;

    @ApiModelProperty(value = "启用禁用")
    private Integer disableFlag;
}
