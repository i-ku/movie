package com.movie.api.service;

import com.movie.api.model.entity.LeavingMessage;
import com.movie.api.model.entity.User;
import com.movie.api.model.vo.ActiveUserVO;
import com.movie.api.model.vo.LeavingMessageVO;

import java.util.List;

public interface LeavingMessageService {

    void save(LeavingMessage leavingMessage);

    void reply(LeavingMessage leavingMessage);

    List<LeavingMessageVO> findAll();

    //获取活跃留言的用户
    List<ActiveUserVO> findActiveUsers();

}
