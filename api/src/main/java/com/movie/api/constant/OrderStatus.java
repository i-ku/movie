package com.movie.api.constant;

/**
 * 订单状态
 */
public final class OrderStatus {

    //15分钟未支付 支付失败
    public final static long EXPIRATION_TIME = 1000 * 60 * 15;

    //初始化订单 等待支付
    public final static Integer PAYMENT_WAITING = 0;

    //支付失败
    public final static Integer PAYMENT_FAILED = 1;

    //支付成功
    public final static Integer PAYMENT_SUCCESSFUL = 2;

    //被管理员撤销
    public final static Integer COUNTERMAND = 3;

}
