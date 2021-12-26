package com.yisu.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yisu.common.core.enums.StatusEnum;
import com.yisu.mapper.ActivityMapper;
import com.yisu.model.Activity;
import com.yisu.service.ActivityService;
import org.springframework.stereotype.Service;

/**
 * @author xuyisu
 * @description 活动-业务实现
 * @date 2020/11/29
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    /**
     * 获取欧东信息
     * @param activityId
     * @return
     */
    @Override
    public Activity getActivityByActivityId(Long activityId) {
        Activity activityParam = new Activity();
        activityParam.setActivityId(activityId);
        activityParam.setStatus(StatusEnum.ENABLE.getValue());
        QueryWrapper<Activity> queryActivity = Wrappers.query(activityParam);
        String date = DateUtil.today();
        queryActivity.lt("start_time", date);
        queryActivity.gt("end_time", date);
        return this.getOne(queryActivity);
    }

}
