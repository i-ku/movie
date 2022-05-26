package com.movie.api.service.impl;

import com.movie.api.constant.OrderStatus;
import com.movie.api.mapper.OrderExceptionMapper;
import com.movie.api.mapper.OrderMapper;
import com.movie.api.model.entity.Order;
import com.movie.api.model.entity.OrderException;
import com.movie.api.service.OrderExceptionService;
import com.movie.api.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class OrderExceptionServiceImpl implements OrderExceptionService {

    @Resource
    private OrderExceptionMapper orderExceptionMapper;

    @Override
    public OrderException create(OrderException orderException) {
        orderException.setStatus(false);
        orderException.setId(UUID.randomUUID().toString());
        orderException.setCreateAt(DataTimeUtil.getNowTimeString());
        orderExceptionMapper.insert(orderException);
        return orderException;
    }

    @Override
    public List<OrderException> findAll() {
        return orderExceptionMapper.selectList(null);
    }

    @Override
    public void handleException(OrderException orderException) {
        orderException.setEndAt(DataTimeUtil.getNowTimeString());
        orderExceptionMapper.updateById(orderException);
    }

}
