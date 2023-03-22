package com.example.dashboardback.comment.repository;

import com.example.dashboardback.comment.entity.Comment;
import com.example.dashboardback.comment.entity.QComment;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.example.dashboardback.comment.entity.QComment.comment;

public class CommentRepositoryImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public CommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Comment> findNotDeletedByCommentId(Long commentId) {
        return Optional.ofNullable(queryFactory.selectFrom(comment)
                .where(commentIdEq(commentId),
                        isDeletedCheck())
                .fetchFirst());
    }

    @Override
    public List<Comment> findAllCommentsByMemoId(Long memoId) {
        return queryFactory.selectFrom(comment)
                .where(memoIdEq(memoId))
                .where(comment.isDeleted.eq(false))
                .orderBy(
                        comment.createdAt.asc()
                )
                .fetch();
    }

    private BooleanExpression isDeletedCheck() {
        return comment.isDeleted.eq(false);
    }

    private BooleanExpression commentIdEq(Long commentId) {
        return commentId != null ? comment.commentId.eq(commentId) : null;
    }

    private BooleanExpression memoIdEq(Long memoId) {
        return memoId != null ? comment.memo.memoId.eq(memoId) : null;
    }
}
