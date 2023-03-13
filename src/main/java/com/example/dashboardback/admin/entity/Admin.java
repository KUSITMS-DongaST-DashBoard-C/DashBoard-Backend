package com.example.dashboardback.admin.entity;

import com.example.dashboardback.comment.entity.Comment;
import com.example.dashboardback.global.entity.BaseTimeEntity;
import com.example.dashboardback.memo.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String password;

    private String name;

    private String email;

    private String phoneNumber;

    private String imageUrl;

    private boolean isDeleted;

    @OneToMany(mappedBy="admin")
    private List<Memo> memos=new ArrayList<>();

    @OneToMany(mappedBy="admin")
    private List<Comment> comments=new ArrayList<>();

}
