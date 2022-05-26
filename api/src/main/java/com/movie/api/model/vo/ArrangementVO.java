package com.movie.api.model.vo;

import com.movie.api.model.entity.Arrangement;
import com.movie.api.model.entity.Film;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArrangementVO {

    private List<Arrangement> arrangements;

    private Film film;

}
