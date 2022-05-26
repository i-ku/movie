package com.movie.api.controller;

import com.movie.api.model.entity.OrderException;
import com.movie.api.service.OrderExceptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "订单异常上报接口")
@RequestMapping("/api/oe")
public class OrderExceptionController {

    @Resource
    private OrderExceptionService orderExceptionService;

    @PostMapping("")
    @ApiOperation("添加异常订单")
    public OrderException create(@RequestBody OrderException orderException) {
        return orderExceptionService.create(orderException);
    }

    @GetMapping("")
    @ApiOperation("查询所有异常订单")
    public List<OrderException> findAll() {
        return orderExceptionService.findAll();
    }

    @PutMapping("")
    @ApiOperation("工作人员处理异常订单")
    public void handle(@RequestBody OrderException orderException) {
        orderExceptionService.handleException(orderException);
    }

}
