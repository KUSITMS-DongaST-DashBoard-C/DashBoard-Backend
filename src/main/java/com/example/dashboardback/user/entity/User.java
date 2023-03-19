package com.example.dashboardback.user.entity;

import com.example.dashboardback.global.entity.BaseTimeEntity;
import com.example.dashboardback.loginhistory.entity.LoginHistory;
import com.example.dashboardback.user.constant.UserConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String major;

    @Column
    private Long licenseNum;

    @Column(nullable = false)
    private String hospitalName;

    @Embedded
    private Address address;

    @Column
    private LocalDateTime loginTime;

    @Column
    private boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private UserConstants.Role role;

    @OneToMany(mappedBy = "user")
    private List<LoginHistory> loginHistories = new ArrayList<>();

    public static User createUser(){
        return User.builder()
                .email("skytea@gmail.com")
                .name("이민아")
                .password("adljfladfjdlfefafeiovKFjefj")
                .major("그외")
                .licenseNum(567828L)
                .hospitalName("서울성모병원")
                .address(Address.builder()
                        .city("인천")
                        .town(" ")
                        .detail("101동")
                        .build())
                .role(UserConstants.Role.valueOf("ROLE_USER"))
                .build();
    }

}
