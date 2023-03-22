package com.example.dashboardback.contents.original.repository;

import com.example.dashboardback.contents.original.dto.OriginalDto;
import com.example.dashboardback.contents.original.dto.OriginalDto.DetailInfoRequest;
import com.example.dashboardback.contents.original.dto.OriginalDto.UploadInfoResponse;
import com.example.dashboardback.contents.original.dto.QOriginalDto_DetailInfoResponse;
import com.example.dashboardback.contents.original.dto.QOriginalDto_UploadInfoResponse;
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
                .select(new QOriginalDto_UploadInfoResponse(original.thumbnailUrl,
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
    public List<OriginalDto.DetailInfoResponse> getOrderByHitsDesc(DetailInfoRequest detailInfoRequest) {
        return queryFactory
                .select(new QOriginalDto_DetailInfoResponse(original.thumbnailUrl,
                        original.seriesName,
                        original.episodeNum,
                        original.uploadDate,
                        original.reviewNum,
                        original.likeNum,
                        original.commentNum,
                        original.hits,
                        original.major))
                .from(original)
                .where(original.isUploaded.eq(true))
                .where(original.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .orderBy(original.hits.desc())
                .limit(3)
                .fetch();
    }

    @Override
    public List<OriginalDto.DetailInfoResponse> getOrderByHitsAsc(DetailInfoRequest detailInfoRequest) {
        return queryFactory
                .select(new QOriginalDto_DetailInfoResponse(original.thumbnailUrl,
                        original.seriesName,
                        original.episodeNum,
                        original.uploadDate,
                        original.reviewNum,
                        original.likeNum,
                        original.commentNum,
                        original.hits,
                        original.major))
                .from(original)
                .where(original.isUploaded.eq(true))
                .where(original.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .orderBy(original.hits.asc())
                .limit(3)
                .fetch();
    }

    @Override
    public List<OriginalDto.DetailInfoResponse> getOrderByReplyDesc(DetailInfoRequest detailInfoRequest) {
        return queryFactory
                .select(new QOriginalDto_DetailInfoResponse(original.thumbnailUrl,
                        original.seriesName,
                        original.episodeNum,
                        original.uploadDate,
                        original.reviewNum,
                        original.likeNum,
                        original.commentNum,
                        original.hits,
                        original.major))
                .from(original)
                .where(original.isUploaded.eq(true))
                .where(original.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .orderBy(original.commentNum.desc())
                .limit(3)
                .fetch();
    }

    @Override
    public List<OriginalDto.DetailInfoResponse> getOrderByLikesDesc(DetailInfoRequest detailInfoRequest) {
        return queryFactory
                .select(new QOriginalDto_DetailInfoResponse(original.thumbnailUrl,
                        original.seriesName,
                        original.episodeNum,
                        original.uploadDate,
                        original.reviewNum,
                        original.likeNum,
                        original.commentNum,
                        original.hits,
                        original.major))
                .from(original)
                .where(original.isUploaded.eq(true))
                .where(original.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .orderBy(original.likeNum.desc())
                .limit(3)
                .fetch();
    }

    @Override
    public List<OriginalDto.DetailInfoResponse> getOrderByReviewDesc(DetailInfoRequest detailInfoRequest) {
        return queryFactory
                .select(new QOriginalDto_DetailInfoResponse(original.thumbnailUrl,
                        original.seriesName,
                        original.episodeNum,
                        original.uploadDate,
                        original.reviewNum,
                        original.likeNum,
                        original.commentNum,
                        original.hits,
                        original.major))
                .from(original)
                .where(original.isUploaded.eq(true))
                .where(original.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .orderBy(original.reviewNum.desc())
                .limit(3)
                .fetch();
    }

    @Override
    public Long getViewNum(DetailInfoRequest detailInfoRequest) {
        return queryFactory.select(original.hits.sum())
                .from(original)
                .where(original.isUploaded.eq(true))
                .where(original.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .fetchOne();
    }

    @Override
    public Long getAllViewNum() {
        return queryFactory.select(original.hits.sum())
                .from(original)
                .where(original.isUploaded.eq(true))
                .fetchOne();
    }

    private LocalDateTime toLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime changedDate = LocalDateTime.parse(date+" 00:00:00", formatter);
        return changedDate;
    }
}
