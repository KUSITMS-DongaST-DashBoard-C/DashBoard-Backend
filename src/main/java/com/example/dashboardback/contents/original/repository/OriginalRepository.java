package com.example.dashboardback.contents.original.repository;

import com.example.dashboardback.contents.original.entity.Original;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OriginalRepository extends JpaRepository<Original, Long>, OriginalRepositoryCustom {
}
