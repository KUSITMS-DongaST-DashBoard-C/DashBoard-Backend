package com.example.dashboardback.image.repository;

import com.example.dashboardback.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryCustom {


}
