package com.example.dashboardback.chart.repository;

import com.example.dashboardback.chart.dto.Res.GetMajorNumRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityDailyVisitorsRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityNewMemberRes;
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


//    @Query(value = "select city,\n" +
//            "       (count(*) * 100.0 / (SELECT SUM(user_count)\n" +
//            "                            FROM (SELECT COUNT(city) as user_count FROM user GROUP BY city) as city_counts)\n" +
//            "           )as ratio\n" +
//            "from user\n" +
//            "group by city;", nativeQuery = true)
//    public List<GetCityNumResInterface> getCityNum();

    @Query("select " +
            "new com.example.dashboardback.chart.dto.Res.city.GetCityNewMemberRes(u.address.city, count(u.address.city))" +
            "from User u where (function('date_format', u.createdAt,'%Y-%m-%d')=current_date) " +
            "group by u.address.city")
    public List<GetCityNewMemberRes> getNewMemCnt();

//    @Query("SELECT " +
//            "new com.example.dashboardback.chart.dto.Res.city.GetCityDailyVisitorsRes(u.address.city, COUNT(u.address.city)) " +
//            "FROM LoginHistory h JOIN h.user u " +
//            "WHERE function('date_format', h.loginTime, '%Y-%m-%d') = current_date " +
//            "GROUP BY u.address.city")
//    public List<GetCityDailyVisitorsRes> getVisiotrsCnt();

}
