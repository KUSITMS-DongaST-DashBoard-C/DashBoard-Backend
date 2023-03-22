package com.example.dashboardback.contents.live.repository;

import com.example.dashboardback.comment.entity.Comment;
import com.example.dashboardback.contents.live.entity.Live;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LiveRepository extends JpaRepository<Live, Long> {

    @Query(value = "select * " +
            "from live " +
            "where datediff(live.live_date, curdate())>0  " +
            "order by datediff(live.live_date, curdate()) asc;", nativeQuery = true)
    public List<Live> getExpectedUpload();
}
