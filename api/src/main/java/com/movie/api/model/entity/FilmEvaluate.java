package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 电影评价
 */
@Data
@TableName("t_film_evaluate")
@NoArgsConstructor
public class FilmEvaluate implements Serializable {

    private String id;

    //电影id
    private String fid;

    //用户id
    private String uid;

    //星级
    private Integer star;

    //评语
    private String comment;

    private String createAt;

}
