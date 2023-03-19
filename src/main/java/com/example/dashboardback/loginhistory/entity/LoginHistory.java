package com.example.dashboardback.loginhistory.entity;

import com.example.dashboardback.image.entity.Image;
import com.example.dashboardback.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoginHistory {
    @Id
    @Column(name="loginhistory_id")
    private Long loginHistoryId;

    private LocalDateTime loginTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 연관관계 매핑
     */

    public void setUser(User user) {
        this.user=user;
        user.getLoginHistories().add(this);
    }
}
