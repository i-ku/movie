package com.movie.api.service;

import com.movie.api.model.entity.Activity;

import java.util.List;

public interface ActivityService {

    void create(Activity activity);

    Activity findById(String id);

    List<Activity> findAll();

    void deleteById(String id);

}
