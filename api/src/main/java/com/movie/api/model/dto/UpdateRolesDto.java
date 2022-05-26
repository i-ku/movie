package com.movie.api.model.dto;

import com.movie.api.model.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UpdateRolesDto {

    private String uid;

    private List<Role> roles;

}
