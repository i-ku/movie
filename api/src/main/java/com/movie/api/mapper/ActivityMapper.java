package com.movie.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.api.model.entity.Activity;
import org.mapstruct.Mapper;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
}
