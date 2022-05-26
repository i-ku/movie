package com.movie.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.api.constant.OrderStatus;
import com.movie.api.mapper.FilmMapper;
import com.movie.api.mapper.OrderMapper;
import com.movie.api.mapper.UserMapper;
import com.movie.api.model.entity.Arrangement;
import com.movie.api.model.entity.Cart;
import com.movie.api.model.entity.Film;
import com.movie.api.model.entity.Order;
import com.movie.api.model.vo.OrderVO;
import com.movie.api.service.ArrangementService;
import com.movie.api.service.OrderService;
import com.movie.api.utils.DataTimeUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "order")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ArrangementService arrangementService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private FilmMapper filmMapper;

    @Override
    public Order create(Cart cart) throws Exception {
        List<Integer> seats = arrangementService.getSeatsHaveSelected(cart.getAid());
        String[] split = cart.getSeats().split("号");
        for (String s : split) {
            if (seats.contains(Integer.parseInt(s))) throw new Exception("影片在购物车中躺了太长时间了，座位已被其他用户预订并支付了");
        }
        Order order = new Order();
        //生成订单id
        order.setId(UUID.randomUUID().toString());
        //写入用户id
        order.setUid(cart.getUid());
        //写入用户电话
        order.setPhone(cart.getPhone());
        //写入场次id
        order.setAid(cart.getAid());
        //写入座位信息
        order.setStatus(cart.getStatus());
        order.setSeats(cart.getSeats());
        if (cart.getStatus() == 2) order.setPayAt(DataTimeUtil.getNowTimeString());
        order.setPrice(cart.getPrice());
        order.setCreateAt(DataTimeUtil.getNowTimeString());
        orderMapper.insert(order);

        //订了几个座位就添加多少热度
        Film film = filmMapper.selectById(arrangementService.findById(cart.getAid()).getFid());
        film.setHot(film.getHot() + split.length);
        filmMapper.updateById(film);
        return order;
    }

    @Override
    public Order pay(String id) throws Exception {
        Order order = orderMapper.selectById(id);
        if (order == null) throw new Exception("不存在的订单id");

        if (DataTimeUtil.parseTimeStamp(order.getCreateAt()) + OrderStatus.EXPIRATION_TIME
                < System.currentTimeMillis()) {
            order.setStatus(OrderStatus.PAYMENT_FAILED);
            orderMapper.updateById(order);
            throw new Exception("订单支付超时");
        }

        order.setStatus(OrderStatus.PAYMENT_SUCCESSFUL);
        order.setPayAt(DataTimeUtil.getNowTimeString());
        orderMapper.updateById(order);
        return order;
    }

    @Override
    public void update(Order order) {
        orderMapper.updateById(order);
    }

    @Override
    public List<OrderVO> findAll() {
        return findByWrapper(new QueryWrapper<>());
    }

    @Override
    public List<OrderVO> findByUser(String uid) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.in("uid", uid);
        return findByWrapper(wrapper);
    }

    private List<OrderVO> findByWrapper(QueryWrapper<Order> wrapper) {
        List<Order> orders = orderMapper.selectList(wrapper);
        List<OrderVO> result = new ArrayList<>();
        for (Order o : orders) {
            OrderVO orderVO = new OrderVO();
            orderVO.setOrder(o);
            orderVO.setUser(userMapper.selectById(o.getUid()));
            Arrangement arrangement = arrangementService.findById(o.getAid());
            orderVO.setArrangement(arrangement);
            orderVO.setFilm(filmMapper.selectById(arrangement.getFid()));
            result.add(orderVO);
        }
        return result;
    }

}
