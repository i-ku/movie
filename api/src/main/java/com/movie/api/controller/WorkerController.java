package com.movie.api.controller;

import com.movie.api.constant.Roles;
import com.movie.api.model.dto.LoginDto;
import com.movie.api.model.entity.Role;
import com.movie.api.model.entity.Worker;
import com.movie.api.service.RoleService;
import com.movie.api.service.WorkerService;
import com.movie.api.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "员工接口")
@RequestMapping("/api/worker")
public class WorkerController {

    @Resource
    private WorkerService workerService;

    @Resource
    private RoleService roleService;

    @PostMapping("/login")
    @ApiOperation("员工登录")
    public Map<String, Object> login(@RequestBody LoginDto dto) throws Exception {
        Worker worker = workerService.login(dto);
        Map<String, Object> map = new HashMap<>();
        //是否选择记住我
        long exp = dto.isRemember() ? JwtTokenUtil.REMEMBER_EXPIRATION_TIME : JwtTokenUtil.EXPIRATION_TIME;

        //查询登录的客服具有哪些权限
        List<String> roles = new ArrayList<>();
        //添加最基本的客服权限
        roles.add(Roles.ROLE_WORKER);

        for (Role role : roleService.listRolesByWorkerId(worker.getId())){
            roles.add(role.getValue());
        }
        map.put("token", JwtTokenUtil.createToken(dto.getUsername(), roles, exp));
        map.put("worker", worker);
        return map;
    }

    @PostMapping("")
    @ApiOperation("添加员工")
    public Worker create(@RequestBody Worker worker) throws Exception {
        return workerService.create(worker);
    }

    @GetMapping("")
    @ApiOperation("查询全部员工")
    public List<Worker> list(){
        return workerService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询员工")
    public Worker findById(@PathVariable String id){
        return workerService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除员工")
    public void deleteById(@PathVariable String id){
        workerService.deleteById(id);
    }

    @PutMapping("")
    @ApiOperation("更新员工信息")
    public void update(@RequestBody Worker worker) throws Exception {
        workerService.update(worker);
    }

}
