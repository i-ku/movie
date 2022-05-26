package com.movie.api.controller;

import com.movie.api.model.entity.DailyWork;
import com.movie.api.service.DailyWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "每日工作接口")
@RequestMapping("/api/daily")
public class DailyWorkController {

    @Resource
    private DailyWorkService dailyWorkService;

    @PostMapping("")
    @ApiOperation("添加每日工作")
    public void create(@RequestBody DailyWork dailyWork){
        dailyWorkService.save(dailyWork);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除每日工作")
    public void delete(@PathVariable String id){
        dailyWorkService.deleteById(id);
    }

    @GetMapping("")
    @ApiOperation("查询所有")
    public List<DailyWork> findAll(){
        return dailyWorkService.findAll();
    }

}
