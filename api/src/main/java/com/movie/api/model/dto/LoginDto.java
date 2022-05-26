package com.movie.api.model.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String username;

    private String password;

    private boolean remember;

}
