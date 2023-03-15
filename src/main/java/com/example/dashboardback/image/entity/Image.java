package com.example.dashboardback.image.entity;

import com.example.dashboardback.global.entity.BaseTimeEntity;
import com.example.dashboardback.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends BaseTimeEntity {

    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String imageKey;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isDeleted;

    public Image(String imageKey) {
        this.imageKey = imageKey;
    }

    public static Image from(String key) {
        return Image.builder()
                .imageKey(key)
                .build();
    }
}
