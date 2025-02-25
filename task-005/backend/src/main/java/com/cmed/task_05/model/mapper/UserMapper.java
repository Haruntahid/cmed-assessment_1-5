package com.cmed.task_05.model.mapper;

import com.cmed.task_05.model.dtos.UserDto;
import com.cmed.task_05.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User dtoToUser(UserDto dto);

}
