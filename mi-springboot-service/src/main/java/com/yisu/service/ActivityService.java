package com.yisu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yisu.model.Activity;

/**
 * @description 活动-业务接口
 * @author xuyisu
 * @date 2020-11-29
 */
public interface ActivityService extends IService<Activity> {

    /**
     * 获取欧东信息
     * @param activityId
     * @return
     */
    Activity getActivityByActivityId(Long activityId);

}

