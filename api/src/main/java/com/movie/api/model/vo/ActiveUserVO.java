package com.movie.api.model.vo;

import com.movie.api.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUserVO {

    private User user;

    private Integer Number;

}
