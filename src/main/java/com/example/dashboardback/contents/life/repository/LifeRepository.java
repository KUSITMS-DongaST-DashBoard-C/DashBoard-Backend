package com.example.dashboardback.contents.life.repository;

import com.example.dashboardback.contents.life.entity.Life;
import com.example.dashboardback.contents.live.entity.Live;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LifeRepository extends JpaRepository<Life, Long> {

    @Query(value = "select * " +
            "from life " +
            "where datediff(life.upload_date, curdate())>=0  " +
            "order by datediff(life.upload_date, curdate()) asc;", nativeQuery = true)
    public List<Life> getExpectedUpload();

}
