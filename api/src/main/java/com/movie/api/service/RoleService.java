package com.movie.api.service;

import com.movie.api.model.entity.Role;

import java.util.List;

public interface RoleService {

    Role create(Role role) throws Exception;

    void deleteById(String id) throws Exception;

    List<Role> listRolesByWorkerId(String wid);

    void deleteWorkerAllRoles(String wid);

}
