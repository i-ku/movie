package com.movie.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.api.model.entity.Registration;
import org.mapstruct.Mapper;

@Mapper
public interface RegistrationMapper extends BaseMapper<Registration> {
}
