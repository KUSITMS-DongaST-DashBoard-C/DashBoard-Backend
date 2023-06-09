package com.example.dashboardback.admin.entity;


import com.example.dashboardback.comment.entity.Comment;
import com.example.dashboardback.image.entity.Image;
import com.example.dashboardback.memo.entity.Memo;
import com.example.dashboardback.admin.constant.AdminConstants.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "admin")
    private Image adminImage;

    @OneToMany(mappedBy="admin")
    private List<Memo> memos=new ArrayList<>();

    @OneToMany(mappedBy="admin")
    private List<Comment> comments=new ArrayList<>();

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    /**
     * 연관관계 매핑
     */

    public void setUserImage(Image image) {
        this.adminImage = image;
        image.setAdmin(this);
    }


}
