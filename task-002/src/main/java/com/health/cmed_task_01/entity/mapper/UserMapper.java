package com.health.cmed_task_01.entity.mapper;

import com.health.cmed_task_01.entity.dtos.UsersDto;
import com.health.cmed_task_01.entity.model.Users;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    Users toEntity(UsersDto dto, @Context PasswordEncoder passwordEncoder);
}
