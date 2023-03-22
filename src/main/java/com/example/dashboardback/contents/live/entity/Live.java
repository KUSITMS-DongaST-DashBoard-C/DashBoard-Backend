package com.example.dashboardback.contents.live.entity;


import com.example.dashboardback.global.entity.BaseTimeEntity;
import lombok.*;
import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Live extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="live_id")
    private Long liveId;

    @Temporal(TemporalType.DATE)
    private Date liveDate;

    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Temporal(TemporalType.TIME)
    private Date endTime;

    @Column
    private String title;

    @Column
    private String target;

    @Embedded
    private Lecturer lecturer;

    @Column
    private Long applicableNum;

    @Column
    private Long applicantNum;

    @Column
    private String thumbnailUrl;

    @Column
    private String videoUrl;

    @Column
    private Long viewNum;

    @Column
    private boolean isDeleted;

}
