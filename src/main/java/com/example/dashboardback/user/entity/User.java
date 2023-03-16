package com.example.dashboardback.user.entity;


import com.example.dashboardback.comment.entity.Comment;
import com.example.dashboardback.image.entity.Image;
import com.example.dashboardback.memo.entity.Memo;
import com.example.dashboardback.user.constant.UserConstants;
import com.example.dashboardback.user.constant.UserConstants.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    private String name;

    private String email;

    private String phoneNumber;

    private boolean isDeleted;

    @OneToOne(mappedBy = "user")
    private Image userImage;

    @OneToMany(mappedBy="user")
    private List<Memo> memos=new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<Comment> comments=new ArrayList<>();

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    /**
     * 연관관계 매핑
     */

    public void setUserImage(Image image) {
        this.userImage = image;
        image.setUser(this);
    }


}
