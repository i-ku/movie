package com.movie.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.api.constant.Roles;
import com.movie.api.mapper.AdminMapper;
import com.movie.api.model.dto.LoginDto;
import com.movie.api.model.entity.Admin;
import com.movie.api.service.AdminService;
import com.movie.api.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public String login(LoginDto loginDto) throws Exception {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.in("username", loginDto.getUsername());
        wrapper.in("password", loginDto.getPassword());
        Admin admin = adminMapper.selectOne(wrapper);
        if (admin == null) throw new Exception("用户名密码错误");
        //是否选择记住我
        long exp = loginDto.isRemember() ? JwtTokenUtil.REMEMBER_EXPIRATION_TIME : JwtTokenUtil.EXPIRATION_TIME;
        List<String> roles = new ArrayList<>();
        roles.add(Roles.ROLE_ADMIN);
        return JwtTokenUtil.createToken(loginDto.getUsername(), roles, exp);
    }

}
