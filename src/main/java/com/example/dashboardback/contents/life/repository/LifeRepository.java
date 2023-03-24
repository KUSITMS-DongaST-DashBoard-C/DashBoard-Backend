package com.example.dashboardback.contents.life.repository;

import com.example.dashboardback.contents.life.entity.Life;
import com.example.dashboardback.contents.live.entity.Live;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LifeRepository extends JpaRepository<Life, Long> {

    @Query(value = "select * " +
            "from life " +
            "where datediff(life.upload_date, curdate())>=0  " +
            "order by datediff(life.upload_date, curdate()) asc;", nativeQuery = true)
    public List<Life> getExpectedUpload();

    @Query(value ="select * " +
            "from life " +
            "where datediff(life.upload_date, curdate())<0  " +
            "order by datediff(life.upload_date, curdate()) desc;", nativeQuery = true)
    public List<Life> getUploaded();

    @Query("select sum(f.viewNum) from Life f")
    public Long getAllViewNum();

    @Query(value="select sum(life.view_num) " +
            "from life " +
            "where life.upload_date>=:endDate and life.upload_date<=:startDate " +
            "order by life.view_num;", nativeQuery = true)
    public Long getViewNum(@Param("startDate")String startDate, @Param("endDate")String endDate);

    @Query(value="select * " +
            "from life " +
            "where life.upload_date>= :endDate and life.upload_date<= :startDate "+
            "order by life.view_num ASC ;", nativeQuery = true)
    public List<Life> getLifeOrderbyViewNumASC(@Param("startDate")String startDate, @Param("endDate")String endDate);

    @Query(value="select * " +
            "from life " +
            "where life.upload_date>= :endDate and life.upload_date<= :startDate "+
            "order by life.view_num DESC ;", nativeQuery = true)
    public List<Life> getLifeOrderbyViewNumDESC(@Param("startDate")String startDate, @Param("endDate")String endDate);


    @Query(value="select * " +
            "from life " +
            "where life.upload_date>=:endDate and life.upload_date<=:startDate " +
            "order by life.comment_num desc;", nativeQuery = true)
    public List<Life> getLifeOrderByCommentNum(@Param("startDate")String startDate, @Param("endDate")String endDate);

    @Query(value="select * " +
            "from life " +
            "where life.upload_date>=:endDate and life.upload_date<=:startDate " +
            "order by life.like_num desc;", nativeQuery = true)
    public List<Life> getLifeOrderByLikeNum(@Param("startDate")String startDate, @Param("endDate")String endDate);
}
