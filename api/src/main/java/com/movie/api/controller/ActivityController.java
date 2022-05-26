package com.movie.api.controller;

import com.movie.api.model.entity.Activity;
import com.movie.api.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "活动接口")
@RequestMapping("/api/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @PostMapping("")
    @ApiOperation("新增活动")
    public void create(@RequestBody Activity activity) {
        activityService.create(activity);
    }

    @GetMapping("")
    @ApiOperation("获取全部活动")
    public List<Activity> findAll() {
        return activityService.findAll();
    }

    @GetMapping("{id}")
    @ApiOperation("根据id查找活动")
    public Activity findById(@PathVariable String id) {
        return activityService.findById(id);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除活动")
    public void delete(@PathVariable String id) {
        activityService.deleteById(id);
    }

}
