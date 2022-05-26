package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 订单异常
 */
@Data
@TableName("t_order_exception")
@NoArgsConstructor
@AllArgsConstructor
public class OrderException implements Serializable {

    private String id;

    //订单id
    private String oid;

    //异常原因
    private String reason;

    //审核人 申报人
    private String reviewer;

    //true 已处理
    //false 待处理
    private boolean status;

    private String result;

    private String createAt;

    private String endAt;

}
