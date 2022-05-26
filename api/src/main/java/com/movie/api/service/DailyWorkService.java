package com.movie.api.service;

import com.movie.api.model.entity.DailyWork;

import java.util.List;

public interface DailyWorkService {

    void save(DailyWork dailyWork);

    List<DailyWork> findAll();

    void deleteById(String id);

}
