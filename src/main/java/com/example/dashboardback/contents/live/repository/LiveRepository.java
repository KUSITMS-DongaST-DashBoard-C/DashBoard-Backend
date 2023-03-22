package com.example.dashboardback.contents.live.repository;

import com.example.dashboardback.comment.entity.Comment;
import com.example.dashboardback.contents.live.entity.Live;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LiveRepository extends JpaRepository<Live, Long> {

    @Query(value = "select * " +
            "from live " +
            "where datediff(live.live_date, curdate())>=0  " +
            "order by datediff(live.live_date, curdate()) asc;", nativeQuery = true)
    public List<Live> getExpectedUpload();

    @Query(value ="select * " +
            "from live " +
            "where datediff(live.live_date, curdate())<0  " +
            "order by datediff(live.live_date, curdate()) desc;", nativeQuery = true)
    public List<Live> getUploaded();

    @Query(value="select * " +
            "from live " +
            "where live.live_date>= :endDate and live.live_date<= :startDate "+
            "order by live.view_num ASC ;", nativeQuery = true)
    public List<Live> getLiveOrderbyViewNumASC(@Param("startDate")String startDate, @Param("endDate")String endDate);

    @Query(value="select * " +
            "from live " +
            "where live.live_date>= :endDate and live.live_date<= :startDate "+
            "order by live.view_num DESC ;", nativeQuery = true)
    public List<Live> getLiveOrderbyViewNumDESC(@Param("startDate")String startDate, @Param("endDate")String endDate);


    @Query(value="select sum(live.view_num) " +
            "from live " +
            "where live.live_date>=:endDate and live.live_date<=:startDate " +
            "order by live.view_num;", nativeQuery = true)
    public Long getViewNum(@Param("startDate")String startDate, @Param("endDate")String endDate);

    @Query(value="select * " +
            "from live " +
            "where live.live_date>=:endDate and live.live_date<=:startDate " +
            "order by live.applicant_num desc;", nativeQuery = true)
    public List<Live> getLiveOrderByApplicantNum(@Param("startDate")String startDate, @Param("endDate")String endDate);
}
