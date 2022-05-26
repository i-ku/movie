package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 客服评价
 */
@Data
@TableName("t_worker_evaluate")
@NoArgsConstructor
public class WorkerEvaluate implements Serializable {

    private String id;

    //客服id
    private String wid;

    //评价的用户id
    private String uid;

    //评价内容
    private String content;

    //满意 非常满意
    private String type;

    private String createAt;

}
