package com.cmed.task_04.entity.mapper;


import com.cmed.task_04.entity.dtos.UsersDto;
import com.cmed.task_04.entity.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users dtoToUser(UsersDto usersDto);

}
