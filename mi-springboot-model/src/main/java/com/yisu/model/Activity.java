package com.yisu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yisu.common.core.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @description 活动-实体
 * @author xuyisu
 * @date '2020-11-29 15:25:00'.
 */
@Data
@TableName("activity")
@EqualsAndHashCode(callSuper=false)
public class Activity extends BaseModel {

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Long activityId;
    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称")
    private String name;
    /**
     * 活动状态
     */
    @ApiModelProperty(value = "活动状态")
    private Integer status;
    /**
     * 活动图片地址
     */
    @ApiModelProperty(value = "活动图片地址")
    private String mainImage;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
}
