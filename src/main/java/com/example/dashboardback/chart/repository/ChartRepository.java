package com.example.dashboardback.chart.repository;

import com.example.dashboardback.chart.dto.Res.GetMajorNumRes;
import com.example.dashboardback.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChartRepository extends JpaRepository<User, Long > {

    @Query("select " +
            "new com.example.dashboardback.chart.dto.Res.GetMajorNumRes(u.major, count(u.major)) " +
            "from User u " +
            "group by u.major order by count(u.major) desc")
    public List<GetMajorNumRes> getMajorNum();
}
