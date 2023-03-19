package com.example.dashboardback.admin.dto;

import com.example.dashboardback.admin.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses= AdminMapperSupport.class)
public interface AdminMapper {
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target="name", source="name")
    @Mapping(target="phoneNumber", source="phoneNumber")
    @Mapping(target = "userImage", source = "file", qualifiedByName = "saveImage")
    Admin toEntity(AdminDto.SignupRequest signupRequest);
}
