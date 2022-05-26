package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 影院活动
 */
@Data
@TableName("t_activity")
@NoArgsConstructor
public class Activity implements Serializable {

    private String id;

    private String content;

    private Integer number;

    private String startTime;

    private String endTime;

    private String createAt;

}
