package com.movie.api.controller;

import com.movie.api.model.entity.Cart;
import com.movie.api.model.vo.CartVO;
import com.movie.api.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "用户购物车接口")
@RequestMapping("/api/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @PostMapping
    @ApiOperation("添加购物车")
    public void save(@RequestBody Cart cart) throws Exception {
        cartService.save(cart);
    }

    @GetMapping("")
    @ApiOperation("根据用户id查询购物车")
    public List<CartVO> list(@RequestParam String uid) {
        return cartService.findAllByUserId(uid);
    }

    @DeleteMapping("")
    @ApiOperation("删除购物车")
    public void delete(String id) {
        cartService.deleteById(id);
    }

}
