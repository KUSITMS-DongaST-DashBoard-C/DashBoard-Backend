package com.example.dashboardback.contents.original.entity;

import com.example.dashboardback.contents.Contents;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Original extends Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="original_id")
    private Long originalId;

    private String seriesName;

    private Integer episodeNum;

    private Integer reviewNum;

    private Double rating;

    private Integer likeNum;

    private Integer commentNum;

}
