package com.example.dashboardback.memo.entity;

import com.example.dashboardback.user.entity.User;
import com.example.dashboardback.comment.entity.Comment;
import com.example.dashboardback.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Memo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoId;

    private String content;

    private boolean isDeleted;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="memo")
    private List<Comment> comments=new ArrayList<>();

    /**
     * 연관관계 매핑
     */

    public void setUser(User user){
        this.user = user;
        user.getMemos().add(this);
    }
}
