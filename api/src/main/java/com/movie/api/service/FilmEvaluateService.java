package com.movie.api.service;

import com.movie.api.model.entity.FilmEvaluate;
import com.movie.api.model.vo.FilmEvaluateVO;

import java.util.List;

public interface FilmEvaluateService {

    void save(FilmEvaluate filmEvaluate) throws Exception;

    List<FilmEvaluateVO> findAllByFilmId(String fid);

    void deleteAllByFilmId(String fid);

    void deleteById(String id);

}
