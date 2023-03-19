package com.example.dashboardback.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

       private String city;
       private String town;
       private String detail;

}
