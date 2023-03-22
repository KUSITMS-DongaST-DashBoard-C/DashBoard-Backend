package com.example.dashboardback.contents.original.repository;

import com.example.dashboardback.contents.original.dto.OrigianlDto.UploadInfoResponse;
import com.example.dashboardback.contents.original.dto.QOrigianlDto_UploadInfoResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.dashboardback.contents.original.entity.QOriginal.original;

public class OriginalRepositoryImpl implements OriginalRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public OriginalRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<UploadInfoResponse> getUpload() {
        return queryFactory
                .select(new QOrigianlDto_UploadInfoResponse(original.thumbnailUrl,
                original.title,
                original.major,
                original.uploadDate))
                .from(original)
                .where(original.isUploaded.eq(false))
                .orderBy(original.uploadDate.asc())
                .limit(3)
                .fetch();
    }
}
