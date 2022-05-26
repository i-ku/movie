package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 影院客服
 */
@Data
@TableName("t_worker")
@NoArgsConstructor
@AllArgsConstructor
public class Worker implements Serializable {

    private String id;

    private String username;

    private String password;

    private String nickname;

    private String gender;

    private String phone;

    private String avatar;

    //入职
    private boolean entry;

    //部门
    private String department;

    private String createAt;

    private String updateAt;

}
