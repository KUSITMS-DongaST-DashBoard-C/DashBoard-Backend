package com.example.dashboardback.user.dto;

import com.example.dashboardback.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses= UserMapperSupport.class)
public interface UserMapper {
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target="name", source="name")
    @Mapping(target="phoneNumber", source="phoneNumber")
    @Mapping(target = "userImage", source = "file", qualifiedByName = "saveImage")
    User toEntity(UserDto.SignupRequest signupRequest);
}
