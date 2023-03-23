package com.example.dashboardback.contents.life.service;

import com.example.dashboardback.contents.life.dto.Res.GetExpectedUploadRes;
import com.example.dashboardback.contents.life.dto.Res.GetFilteredLife;
import com.example.dashboardback.contents.life.dto.Res.GetFilteredLifeRes;
import com.example.dashboardback.contents.life.dto.Res.GetUploadedRes;
import com.example.dashboardback.contents.life.entity.Life;
import com.example.dashboardback.contents.life.repository.LifeRepository;
import com.example.dashboardback.contents.live.dto.Req.DateReq;
import com.example.dashboardback.contents.live.dto.Res.GetFilteredLiveRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LifeServiceImpl implements LifeService {

    private final LifeRepository lifeRepository;
    @Override
    public List<GetExpectedUploadRes> getExpectedUpload() {
        List<GetExpectedUploadRes> collect = lifeRepository.getExpectedUpload().stream()
                .map(m -> new GetExpectedUploadRes(m.getTitle(),m.getCategory(), m.getThumbnailUrl(),m.getUploadDate()))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<GetUploadedRes> getUploaded() {
        List<GetUploadedRes> collect = lifeRepository.getUploaded().stream()
                .map(m -> new GetUploadedRes(m.getTitle(),m.getCategory(), m.getThumbnailUrl(),m.getUploadDate()))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public GetFilteredLifeRes getLifeOrderByViewNum(DateReq dateReq, String orderBy) {
        Long viewNum = lifeRepository.getViewNum(dateReq.getStartDate(),dateReq.getEndDate());
        List<Life> lifeList = null;

        if(orderBy.equals("asc")) lifeList = lifeRepository.getLifeOrderbyViewNumASC(dateReq.getStartDate(),dateReq.getEndDate());
        else lifeList=lifeRepository.getLifeOrderbyViewNumDESC(dateReq.getStartDate(),dateReq.getEndDate());

        List<GetFilteredLife> collect = lifeList.stream()
                .map(m -> new GetFilteredLife(m.getTitle(),m.getCategory(), m.getThumbnailUrl(),m.getUploadDate(),m.getViewNum(),m.getCommentNum(),m.getLikeNum()))
                .collect(Collectors.toList());

        return new GetFilteredLifeRes(viewNum,collect);
    }
}
