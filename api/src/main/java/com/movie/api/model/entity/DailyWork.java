package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 员工的每日工作
 */
@Data
@TableName("t_daily_work")
public class DailyWork implements Serializable {

    private String id;

    //重要 一般 非常重要
    private int type;

    //工作内容
    private String content;

    //上传时间
    private String createAt;

}
