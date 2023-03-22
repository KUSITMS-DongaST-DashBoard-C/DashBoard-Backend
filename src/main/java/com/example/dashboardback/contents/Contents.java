package com.example.dashboardback.contents;

import com.example.dashboardback.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class Contents extends BaseTimeEntity {

    private String thumbnailUrl;

    private String title;

    private String description;

    private LocalDateTime deadlineDate;

    private String major;

    private Long hits;

    private Boolean isDeleted;

    private Boolean isUploaded;

    private LocalDateTime uploadDate;
}
