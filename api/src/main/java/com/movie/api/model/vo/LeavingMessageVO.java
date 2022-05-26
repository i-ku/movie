package com.movie.api.model.vo;

import com.movie.api.model.entity.LeavingMessage;
import com.movie.api.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LeavingMessageVO implements Serializable {

    private String id;

    private LeavingMessage leavingMessage;

    private User user;

}
