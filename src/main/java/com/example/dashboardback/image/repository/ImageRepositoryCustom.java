package com.example.dashboardback.image.repository;

import com.example.dashboardback.image.entity.Image;

import java.util.Optional;

public interface ImageRepositoryCustom {
    Optional<Image> findNotDeletedByImageId(Long imageId);
    String findByUserId(Long userId);
}
