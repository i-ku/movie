package com.movie.api.model.vo;

import com.movie.api.model.entity.Arrangement;
import com.movie.api.model.entity.Film;
import com.movie.api.model.entity.Order;
import com.movie.api.model.entity.User;
import lombok.Data;

@Data
public class OrderVO {

    private Order order;

    private User user;

    private Film film;

    private Arrangement arrangement;

}
