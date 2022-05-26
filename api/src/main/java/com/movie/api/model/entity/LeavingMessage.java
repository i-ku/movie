package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 留言
 */
@Data
@TableName("t_leaving_message")
@NoArgsConstructor
public class LeavingMessage implements Serializable {

    private String id;

    //用户ID
    private String uid;

    private String reply;

    //内容
    private String content;

    private String createAt;

}
