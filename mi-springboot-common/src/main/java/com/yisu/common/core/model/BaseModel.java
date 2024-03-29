package com.yisu.common.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description 基础实体类
 * @author xuyisu
 * @date 2019-9-20
 */
@Data
public class BaseModel {

    private static final long serialVersionUID = 42L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonIgnore
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    /**
     * 创建人编码
     */
    @JsonIgnore
    @ApiModelProperty(value = "创建人id")
    private String createUser;
    /**
     * 修改人编码
     */
    @JsonIgnore
    @ApiModelProperty(value = "修改人id")
    private String updateUser;
    /**
     * 删除标记(1 删除 0未删除)
     */
    @JsonIgnore
    @ApiModelProperty(value = "删除标记(1 删除 0未删除)")
    @TableLogic
    private Integer deleteFlag;

}
