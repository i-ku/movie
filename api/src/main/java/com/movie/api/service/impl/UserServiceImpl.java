package com.movie.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.api.mapper.UserMapper;
import com.movie.api.model.dto.LoginDto;
import com.movie.api.model.entity.User;
import com.movie.api.service.UserService;
import com.movie.api.utils.DataTimeUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User login(LoginDto dto) throws Exception {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.in("username", dto.getUsername());
        User user = userMapper.selectOne(wrapper);
        if (user == null){
            throw new Exception("用户名或密码错误");
        }
        if (!bCryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new Exception("用户名或密码错误");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public User findById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public User update(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.updateById(user);
        return user;
    }

    @Override
    public User save(User user) throws Exception {
        if (findByUsername(user.getUsername()) != null) {
            throw new Exception("用户名已注册");
        }
        String now = DataTimeUtil.getNowTimeString();
        user.setId(UUID.randomUUID().toString());
        user.setCreateAt(now);
        user.setUpdateAt(now);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void deleteById(String id) {
        userMapper.deleteById(id);
    }

}
