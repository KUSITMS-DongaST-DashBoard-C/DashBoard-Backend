package com.example.dashboardback.contents.vod.service;

import com.example.dashboardback.contents.vod.dto.VodDto;
import com.example.dashboardback.contents.vod.entity.Vod;
import com.example.dashboardback.contents.vod.repository.VodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class VodServiceImpl implements VodService{

    private final VodRepository vodRepository;

    @Override
    public void createVod() {
        Vod vod=new Vod();
        vod.setTags("#비염#개원의#전공의#ENT#이비인후과#인하대병원#김영효");
        vod.setThumbnailUrl("https://dashboard-myprofile.s3.ap-northeast-2.amazonaws.com/vod6.jpeg");
        vod.setTitle("비염의 분류 및 치료");
        vod.setDescription("비염의 분류 및 치료");
        vod.setDeadlineDate(LocalDateTime.of(2023,05,05,23,59));
        vod.setMajor("정형외과");
        vod.setHits(2956L);
        vod.setIsDeleted(false);
        vod.setIsUploaded(true);
        vod.setUploadDate(LocalDateTime.of(2023,03,22,9,00));
        vodRepository.save(vod);
    }

    @Override
    public List<VodDto.UploadInfoResponse> getUpload() {
        return vodRepository.getUpload();
    }

    @Override
    public List<VodDto.DetailInfoResponse> getOrderByHitsDesc(VodDto.DetailInfoRequest detailInfoRequest) {
        return vodRepository.getOrderByHitsDesc(detailInfoRequest);
    }

    @Override
    public List<VodDto.DetailInfoResponse> getOrderByHitsAsc(VodDto.DetailInfoRequest detailInfoRequest) {
        return vodRepository.getOrderByHitsAsc(detailInfoRequest);
    }

    @Override
    public Long getHits(VodDto.DetailInfoRequest detailInfoRequest) {
        return vodRepository.getViewNum(detailInfoRequest);
    }


}
