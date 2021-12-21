package com.yisu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yisu.common.core.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description 类目-实体
 * @author xuyisu
 * @date 2020-11-29 15:42:05
 */
@Data
@TableName("category")
@EqualsAndHashCode(callSuper=false)
public class Category extends BaseModel {

    /**
     * 父id
     */
    @JsonIgnore
    @ApiModelProperty(value = "父id")
    private Long parentId;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 启用禁用状态
     */
    @JsonIgnore
    @ApiModelProperty(value = "启用禁用状态")
    private Integer status;
    /**
     * 排序
     */
    @JsonIgnore
    @ApiModelProperty(value = "排序")
    private Integer sortOrder;
}
