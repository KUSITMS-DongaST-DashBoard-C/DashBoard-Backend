package com.example.dashboardback.contents.live.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecturer {
    private String email;
    private String name;
    private String hospitalName;
    private String major;
}
