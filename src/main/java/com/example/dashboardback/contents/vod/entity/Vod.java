package com.example.dashboardback.contents.vod.entity;

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
public class Vod extends Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vod_id")
    private Long vodId;

    private String tags;

}
