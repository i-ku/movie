package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 权限角色
 */
@Data
@TableName("t_role")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    private String id;

    private String wid;

    private String value;

    private String createAt;

}
