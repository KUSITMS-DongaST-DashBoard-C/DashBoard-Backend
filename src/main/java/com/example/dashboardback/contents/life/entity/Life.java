package com.example.dashboardback.contents.life.entity;

import com.example.dashboardback.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Life extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="life_id")
    private Long lifeId;

    @Temporal(TemporalType.DATE)
    private Date uploadDate;

    @Column
    private String title;

    @Column
    private String category;

    @Column
    private String name;

    @Column
    private String thumbnailUrl;

    @Column
    private Long commentNum;

    @Column
    private Long likeNum;

    @Column
    private Long viewNum;





}
