package com.example.dashboardback.memo.entity;

import com.example.dashboardback.comment.entity.Comment;
import com.example.dashboardback.global.entity.BaseTimeEntity;
import com.example.dashboardback.admin.entity.Admin;
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
@Builder
@Data
public class Memo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="memo_id")
    private Long memoId;

    private String content;

    private boolean isDeleted;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="admin_id")
    private Admin admin;

    @OneToMany(mappedBy="memo")
    private List<Comment> comments=new ArrayList<>();

    /**
     * 연관관계 매핑
     */


    public void setAdmin(Admin admin){
        this.admin = admin;
        admin.getMemos().add(this);
    }
}
