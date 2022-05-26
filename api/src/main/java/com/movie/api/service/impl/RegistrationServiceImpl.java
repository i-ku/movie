package com.movie.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.api.mapper.ActivityMapper;
import com.movie.api.mapper.RegistrationMapper;
import com.movie.api.model.entity.Activity;
import com.movie.api.model.entity.Registration;
import com.movie.api.service.RegistrationService;
import com.movie.api.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private RegistrationMapper registrationMapper;

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public void create(Registration registration) throws Exception {
        //获取要参加的活动id
        String activityId = registration.getAid();
        Activity activity = activityMapper.selectById(activityId);
        if (activity == null) throw new Exception("请求参数错误");

        //查找用户是否已经参加
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        wrapper.in("aid", activityId);
        wrapper.in("uid", registration.getUid());
        if (registrationMapper.selectOne(wrapper) != null) {
            throw new Exception("您已经参加过此活动哦");
        }

        //判断活动是否未开始
        if (DataTimeUtil.isAfterNow(activity.getStartTime())) {
            throw new Exception("活动还没有开始");
        }
        //判断时候结束
        if (!DataTimeUtil.isAfterNow(activity.getEndTime())) {
            throw new Exception("活动已经结束了");
        }
        activity.setNumber(activity.getNumber() + 1);
        activityMapper.updateById(activity);
        registration.setCreateAt(DataTimeUtil.getNowTimeString());
        registrationMapper.insert(registration);
    }

    @Override
    public Registration findById(String id) {
        return registrationMapper.selectById(id);
    }

    @Override
    public List<Registration> findAll() {
        return registrationMapper.selectList(null);
    }

    @Override
    public void deleteById(String id) {
        registrationMapper.deleteById(id);
    }

}
