package com.movie.api.service;

import com.movie.api.model.entity.OrderException;

import java.util.List;

public interface OrderExceptionService {

    OrderException create(OrderException orderException);

    List<OrderException> findAll();

    void handleException(OrderException orderException);

}
