package com.movie.api.controller;

import com.movie.api.model.entity.LeavingMessage;
import com.movie.api.model.vo.ActiveUserVO;
import com.movie.api.model.vo.LeavingMessageVO;
import com.movie.api.service.LeavingMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "留言接口")
@RequestMapping("/api/lm")
public class LeavingMessageController {

    @Resource
    private LeavingMessageService leavingMessageService;

    @PostMapping("")
    @ApiOperation(value = "新增留言接口")
    public void save(@RequestBody LeavingMessage leavingMessage) {
        leavingMessageService.save(leavingMessage);
    }

    @PutMapping("")
    @ApiOperation("回复留言")
    public void reply(@RequestBody LeavingMessage leavingMessage) {
        leavingMessageService.reply(leavingMessage);
    }

    @GetMapping("")
    @ApiOperation("获取所有影院留言")
    public List<LeavingMessageVO> list() {
        return leavingMessageService.findAll();
    }

    @GetMapping("/active")
    @ApiOperation("获取活跃留言的用户")
    public List<ActiveUserVO> findActiveUsers() {
        return leavingMessageService.findActiveUsers();
    }

}
