package com.movie.api.service;

import com.movie.api.model.entity.Registration;

import java.util.List;

public interface RegistrationService {

    void create(Registration registration) throws Exception;

    Registration findById(String id);

    List<Registration> findAll();

    void deleteById(String id);

}
