package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 电影的排片 播放场次 开放多少个座位
 */
@Data
@TableName("t_arrangement")
@NoArgsConstructor
@AllArgsConstructor
public class Arrangement implements Serializable {

    private String id;

    //电影id
    private String fid;

    //电影名
    private String name;

    //开放多少座位
    private Integer seatNumber;

    //价格
    private double price;

    //放映类型 2D 3D
    private String type;

    //电影开始时间 2021-01-01
    private String date;

    //电影开始时间 19:30:00
    private String startTime;

    //票房统计
    private Integer boxOffice;

    //结束时间
    private String endTime;

    //创建人
    private String founder;

    //创建时间
    private String createAt;

}
