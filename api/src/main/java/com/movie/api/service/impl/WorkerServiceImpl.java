package com.movie.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.api.constant.Roles;
import com.movie.api.mapper.RoleMapper;
import com.movie.api.mapper.WorkerMapper;
import com.movie.api.model.dto.LoginDto;
import com.movie.api.model.entity.Role;
import com.movie.api.model.entity.Worker;
import com.movie.api.service.RoleService;
import com.movie.api.service.WorkerService;
import com.movie.api.utils.DataTimeUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Resource
    private WorkerMapper workerMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Worker create(Worker worker) throws Exception {
        if (workerMapper.selectOne(new QueryWrapper<Worker>().in("username", worker.getUsername())) != null) {
            throw new Exception("已存在的用户名");
        }
        String now = DataTimeUtil.getNowTimeString();
        worker.setEntry(true);
        worker.setPassword(bCryptPasswordEncoder.encode(worker.getPassword()));
        worker.setId(UUID.randomUUID().toString());
        worker.setCreateAt(now);
        worker.setUpdateAt(now);
        workerMapper.insert(worker);
        //添加worker权限
        roleMapper.insert(new Role(UUID.randomUUID().toString(), worker.getId(), Roles.ROLE_WORKER, now));
        return worker;
    }

    @Override
    public Worker login(LoginDto dto) throws Exception {
        QueryWrapper<Worker> wrapper = new QueryWrapper<>();
        wrapper.in("username", dto.getUsername());
        Worker worker = workerMapper.selectOne(wrapper);
        if (worker == null) throw new Exception("用户名或密码错误");
        if (!bCryptPasswordEncoder.matches(dto.getPassword(), worker.getPassword())) throw new Exception("用户名或密码错误");
        return worker;
    }

    @Override
    public void update(Worker worker) throws Exception {
        Worker one = workerMapper.selectOne(new QueryWrapper<Worker>().in("username", worker.getUsername()));
        if (one != null && !one.getId().equals(worker.getId())) {
            throw new Exception("已存在的用户名");
        }
        worker.setPassword(bCryptPasswordEncoder.encode(worker.getPassword()));
        worker.setUpdateAt(DataTimeUtil.getNowTimeString());
        workerMapper.updateById(worker);
    }

    @Override
    public List<Worker> findAll() {
        return workerMapper.selectList(null);
    }

    @Override
    public Worker findById(String id) {
        return workerMapper.selectById(id);
    }

    @Override
    public void deleteById(String id) {
        workerMapper.deleteById(id);
        //删除所有权限
        roleService.deleteWorkerAllRoles(id);
    }

}
