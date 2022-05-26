package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 电影
 */
@Data
@TableName("t_film")
@NoArgsConstructor
@AllArgsConstructor
public class Film implements Serializable {

    private String id;

    private String name;

    //上映时间
    private String releaseTime;

    //类型
    private String type;

    //状态
    private boolean status;

    //地区 中国,美国,韩国 ......
    private String region;

    //电影简介
    private String introduction;

    //热度
    private Integer hot;

    //封面图片
    private String cover;

    //电影时长 单位：分钟
    private Integer duration;

}
