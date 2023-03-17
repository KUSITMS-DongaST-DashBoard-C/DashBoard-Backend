package com.example.dashboardback.memo.repository;

import com.example.dashboardback.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long>, MemoRepositoryCustom{
}
