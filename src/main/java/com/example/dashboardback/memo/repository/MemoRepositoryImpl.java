package com.example.dashboardback.memo.repository;

import com.example.dashboardback.memo.dto.MemoDto.GetAllResponse;
import com.example.dashboardback.memo.dto.QMemoDto_GetAllResponse;
import com.example.dashboardback.memo.entity.Memo;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.example.dashboardback.image.entity.QImage.image;
import static com.example.dashboardback.memo.entity.QMemo.memo;
import static com.example.dashboardback.user.entity.QUser.user;

public class MemoRepositoryImpl implements MemoRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public MemoRepositoryImpl(EntityManager em){
        this.jpaQueryFactory=new JPAQueryFactory(em);
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
        List<GetAllResponse> content=jpaQueryFactory
                .select(new QMemoDto_GetAllResponse(memo.memoId,
                        memo.content,
                        memo.createdAt,
                        memo.updatedAt,
                        user.name,
                        user.email,
                        image.imageKey))
                .from(memo, user)
                .leftJoin(memo.user, user)
                .leftJoin(user.userImage, image)
                .where(memo.isDeleted.eq(false))
                .orderBy(memo.createdAt.desc())
                .groupBy(memo.memoId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<GetAllResponse> countQuery=jpaQueryFactory
                .select(new QMemoDto_GetAllResponse(memo.memoId,
                        memo.content,
                        memo.createdAt,
                        memo.updatedAt,
                        user.name,
                        user.email,
                        image.imageKey))
                .from(memo, user)
                .leftJoin(memo.user, user)
                .leftJoin(user.userImage, image)
                .where(memo.isDeleted.eq(false))
                .orderBy(memo.createdAt.desc())
                .groupBy(memo.memoId);
        return PageableExecutionUtils.getPage(content,pageable,()->countQuery.fetchCount());
    }
}
