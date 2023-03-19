package com.example.dashboardback.comment.entity;

import com.example.dashboardback.admin.entity.Admin;
import com.example.dashboardback.global.entity.BaseTimeEntity;
import com.example.dashboardback.memo.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long commentId;

    private String content;

    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Memo_id")
    private Memo memo;

    /**
     * 연관관계 매핑
     */


    public void setMemo(Memo memo){
        this.memo=memo;
        memo.getComments().add(this);
    }

    public void setAdmin(Admin admin){
        this.admin = admin;
        admin.getComments().add(this);
    }
}
