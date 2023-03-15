package com.example.dashboardback.image.dto;

import com.example.dashboardback.image.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "imageKey", source = "key")
    Image toEntity(String key);
}
