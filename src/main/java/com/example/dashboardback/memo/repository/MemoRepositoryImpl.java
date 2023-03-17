package com.example.dashboardback.memo.repository;

import com.example.dashboardback.comment.dto.CommentDto.GetResponse;
import com.example.dashboardback.comment.entity.Comment;
import com.example.dashboardback.comment.repository.CommentRepository;
import com.example.dashboardback.memo.dto.MemoDto.GetAllResponse;
import com.example.dashboardback.memo.dto.QMemoDto_GetAllResponse;
import com.example.dashboardback.memo.entity.Memo;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.*;

import static com.example.dashboardback.comment.dto.CommentDto.GetResponse.convertCommentToDto;
import static com.example.dashboardback.image.entity.QImage.image;
import static com.example.dashboardback.memo.entity.QMemo.memo;
import static com.example.dashboardback.user.entity.QUser.user;

public class MemoRepositoryImpl implements MemoRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final CommentRepository commentRepository;

    public MemoRepositoryImpl(EntityManager em, CommentRepository commentRepository){
        this.jpaQueryFactory=new JPAQueryFactory(em);
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Memo> findNotDeletedByMemoId(Long memoId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(memo)
                .where(memo.memoId.eq(memoId),
                        memo.isDeleted.eq(false))
                .fetchFirst());
    }

    @Override
    public Page<GetAllResponse> findAllMemosByCreatedDate(Pageable pageable) {
        List<GetAllResponse> contents=jpaQueryFactory
                .select(new QMemoDto_GetAllResponse(memo.memoId,
                        memo.content,
                        memo.createdAt,
                        memo.updatedAt,
                        user.name,
                        user.email,
                        image.imageUrl))
                .from(memo, user)
                .leftJoin(memo.user, user)
                .leftJoin(user.userImage, image)
                .where(memo.isDeleted.eq(false))
                .orderBy(memo.createdAt.desc())
                .groupBy(memo.memoId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        for(GetAllResponse content:contents){
            content.setComments(getAllCommentsByMemoId(content.getMemoId()));
        }

        JPAQuery<GetAllResponse> countQuery=jpaQueryFactory
                .select(new QMemoDto_GetAllResponse(memo.memoId,
                        memo.content,
                        memo.createdAt,
                        memo.updatedAt,
                        user.name,
                        user.email,
                        image.imageUrl))
                .from(memo, user)
                .leftJoin(memo.user, user)
                .leftJoin(user.userImage, image)
                .where(memo.isDeleted.eq(false))
                .orderBy(memo.createdAt.desc())
                .groupBy(memo.memoId);
        return PageableExecutionUtils.getPage(contents,pageable,()->countQuery.fetchCount());
    }

    private List<GetResponse> getAllCommentsByMemoId(Long memoId) {
        List<Comment> allCommentsByBoardId = this.commentRepository.findAllCommentsByMemoId(memoId);
        List<GetResponse> result = new ArrayList<>();
        Map<Long, GetResponse> map = new HashMap<>();
        allCommentsByBoardId.stream().forEach(c -> {
            GetResponse getResponse = convertCommentToDto(c);
            map.put(getResponse.getCommentId(), getResponse);
            result.add(getResponse);
        });
        return result;
    }

}
