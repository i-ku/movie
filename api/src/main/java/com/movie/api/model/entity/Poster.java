package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页海报
 */
@Data
@TableName("t_poster")
public class Poster implements Serializable {

    private String id;

    private String title;

    private String url;

    //上架 下架
    private boolean status;

    private String createAt;

}
