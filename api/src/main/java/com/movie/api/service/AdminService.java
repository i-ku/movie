package com.movie.api.service;

import com.movie.api.model.dto.LoginDto;

public interface AdminService {

    String login(LoginDto loginDto) throws Exception;

}
