package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 订单
 */
@Data
@TableName("t_order")
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private String id;

    //用户id
    private String uid;

    //取票电话
    private String phone;

    //场次id
    private String aid;

    //座位
    private String seats;

    //金额
    private double price;

    //状态
    private Integer status;

    //创建时间
    private String createAt;

    //更新时间
    private String payAt;

}
