package com.movie.api.service;

import com.movie.api.model.entity.WorkerEvaluate;

import java.util.List;

public interface WorkerEvaluateService {

    void save(WorkerEvaluate workerEvaluate);

    void deleteById(String id);

    //删除该客服的所有评价
    void deleteAllByWid(String wid);

    //根据客服id查询他的所有评价
    List<WorkerEvaluate> findByWorkerId(String wid);

}
