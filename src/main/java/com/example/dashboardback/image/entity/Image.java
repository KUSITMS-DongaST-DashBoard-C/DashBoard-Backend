package com.example.dashboardback.image.entity;

import com.example.dashboardback.global.entity.BaseTimeEntity;
import com.example.dashboardback.admin.entity.Admin;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends BaseTimeEntity implements Serializable {

    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String imageUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    private boolean isDeleted;

    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static Image from(String url) {
        return Image.builder()
                .imageUrl(url)
                .build();
    }
}
