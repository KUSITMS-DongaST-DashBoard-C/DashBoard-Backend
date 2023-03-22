package com.example.dashboardback.contents.vod.repository;

import com.example.dashboardback.contents.vod.dto.QVodDto_DetailInfoResponse;
import com.example.dashboardback.contents.vod.dto.QVodDto_UploadInfoResponse;
import com.example.dashboardback.contents.vod.dto.VodDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.dashboardback.contents.vod.entity.QVod.vod;

public class VodRepositoryImpl implements VodRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public VodRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<VodDto.UploadInfoResponse> getUpload() {
        return queryFactory
                .select(new QVodDto_UploadInfoResponse(vod.thumbnailUrl,
                        vod.title,
                        vod.major,
                        vod.uploadDate))
                .from(vod)
                .where(vod.isUploaded.eq(false))
                .orderBy(vod.uploadDate.asc())
                .limit(3)
                .fetch();
    }

    @Override
    public List<VodDto.DetailInfoResponse> getOrderByHitsDesc(VodDto.DetailInfoRequest detailInfoRequest) {
        return queryFactory
                .select(new QVodDto_DetailInfoResponse(vod.thumbnailUrl,
                        vod.title,
                        vod.vodId,
                        vod.uploadDate,
                        vod.hits))
                .from(vod)
                .where(vod.isUploaded.eq(true))
                .where(vod.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .orderBy(vod.hits.desc())
                .limit(3)
                .fetch();
    }

    @Override
    public List<VodDto.DetailInfoResponse> getOrderByHitsAsc(VodDto.DetailInfoRequest detailInfoRequest) {
        return queryFactory
                .select(new QVodDto_DetailInfoResponse(vod.thumbnailUrl,
                        vod.title,
                        vod.vodId,
                        vod.uploadDate,
                        vod.hits))
                .from(vod)
                .where(vod.isUploaded.eq(true))
                .where(vod.uploadDate.between(toLocalDateTime(detailInfoRequest.getStartDate()),toLocalDateTime(detailInfoRequest.getEndDate())))
                .orderBy(vod.hits.asc())
                .limit(3)
                .fetch();
    }

    private LocalDateTime toLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime changedDate = LocalDateTime.parse(date+" 00:00:00", formatter);
        return changedDate;
    }
}
