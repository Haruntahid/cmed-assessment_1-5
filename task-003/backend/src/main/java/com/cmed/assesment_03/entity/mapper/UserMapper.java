package com.cmed.assesment_03.entity.mapper;


import com.cmed.assesment_03.entity.dtos.UsersDto;
import com.cmed.assesment_03.entity.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users dtoToUser(UsersDto usersDto);

}
