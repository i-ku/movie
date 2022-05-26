package com.movie.api.service;

import com.movie.api.model.entity.Cart;
import com.movie.api.model.vo.CartVO;

import java.util.List;

public interface CartService {

    void save(Cart cart) throws Exception;

    void deleteById(String id);

    void deleteAllByUserId(String uid);

    List<CartVO> findAllByUserId(String uid);

    //删除用户选中的购物车
    void deleteCarts(List<Cart> carts);

    //结算用户选中的购物车
    void settleCarts(List<Cart> carts) throws Exception;

}
