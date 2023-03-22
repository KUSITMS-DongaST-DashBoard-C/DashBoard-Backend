package com.example.dashboardback.contents.original.repository;

import com.example.dashboardback.contents.original.dto.OrigianlDto;
import com.example.dashboardback.contents.original.dto.OrigianlDto.DetailInfoRequest;
import com.example.dashboardback.contents.original.dto.OrigianlDto.UploadInfoResponse;
import com.example.dashboardback.contents.original.dto.QOrigianlDto_DetailInfoResponse;
import com.example.dashboardback.contents.original.dto.QOrigianlDto_UploadInfoResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Override
    public List<OrigianlDto.DetailInfoResponse> getOrderByHitsDesc(DetailInfoRequest detailInfoRequest) {
        return queryFactory
                .select(new QOrigianlDto_DetailInfoResponse(original.thumbnailUrl,
                        original.seriesName,
                        original.episodeNum,
                        original.uploadDate,
                        original.reviewNum,
                        original.likeNum,
                        original.commentNum,
                        original.hits))
                .from(original)
                .where(original.isUploaded.eq(true))
                .where(original.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .orderBy(original.hits.desc())
                .limit(3)
                .fetch();
    }

    @Override
    public List<OrigianlDto.DetailInfoResponse> getOrderByHitsAsc(DetailInfoRequest detailInfoRequest) {
        return queryFactory
                .select(new QOrigianlDto_DetailInfoResponse(original.thumbnailUrl,
                        original.seriesName,
                        original.episodeNum,
                        original.uploadDate,
                        original.reviewNum,
                        original.likeNum,
                        original.commentNum,
                        original.hits))
                .from(original)
                .where(original.isUploaded.eq(true))
                .where(original.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .orderBy(original.hits.asc())
                .limit(3)
                .fetch();
    }

    @Override
    public List<OrigianlDto.DetailInfoResponse> getOrderByReplyDesc(DetailInfoRequest detailInfoRequest) {
        return queryFactory
                .select(new QOrigianlDto_DetailInfoResponse(original.thumbnailUrl,
                        original.seriesName,
                        original.episodeNum,
                        original.uploadDate,
                        original.reviewNum,
                        original.likeNum,
                        original.commentNum,
                        original.hits))
                .from(original)
                .where(original.isUploaded.eq(true))
                .where(original.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .orderBy(original.commentNum.desc())
                .limit(3)
                .fetch();
    }

    @Override
    public List<OrigianlDto.DetailInfoResponse> getOrderByLikesDesc(DetailInfoRequest detailInfoRequest) {
        return queryFactory
                .select(new QOrigianlDto_DetailInfoResponse(original.thumbnailUrl,
                        original.seriesName,
                        original.episodeNum,
                        original.uploadDate,
                        original.reviewNum,
                        original.likeNum,
                        original.commentNum,
                        original.hits))
                .from(original)
                .where(original.isUploaded.eq(true))
                .where(original.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .orderBy(original.likeNum.desc())
                .limit(3)
                .fetch();
    }

    @Override
    public List<OrigianlDto.DetailInfoResponse> getOrderByReviewDesc(DetailInfoRequest detailInfoRequest) {
        return queryFactory
                .select(new QOrigianlDto_DetailInfoResponse(original.thumbnailUrl,
                        original.seriesName,
                        original.episodeNum,
                        original.uploadDate,
                        original.reviewNum,
                        original.likeNum,
                        original.commentNum,
                        original.hits))
                .from(original)
                .where(original.isUploaded.eq(true))
                .where(original.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .orderBy(original.reviewNum.desc())
                .limit(3)
                .fetch();
    }

    private LocalDateTime toLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime changedDate = LocalDateTime.parse(date+" 00:00:00", formatter);
        return changedDate;
    }
}
