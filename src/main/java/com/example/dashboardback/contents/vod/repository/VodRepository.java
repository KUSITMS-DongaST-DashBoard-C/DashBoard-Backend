package com.example.dashboardback.contents.vod.repository;

import com.example.dashboardback.contents.vod.controller.VodController;
import com.example.dashboardback.contents.vod.entity.Vod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VodRepository extends JpaRepository<Vod, Long>, VodRepositoryCustom {
}
