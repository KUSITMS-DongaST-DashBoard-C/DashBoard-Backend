package com.example.dashboardback.contents.live.service;

import com.example.dashboardback.contents.live.dto.Req.DateReq;
import com.example.dashboardback.contents.live.dto.Res.GetExpectedUploadRes;
import com.example.dashboardback.contents.live.dto.Res.GetFilteredLive;
import com.example.dashboardback.contents.live.dto.Res.GetFilteredLiveRes;
import com.example.dashboardback.contents.live.dto.Res.GetUploadedRes;
import com.example.dashboardback.contents.live.entity.Live;
import com.example.dashboardback.contents.live.repository.LiveRepository;
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
public class LiveServiceImpl implements LiveService {

    private final LiveRepository liveRepository;
    @Override
    public List<GetExpectedUploadRes> getExpectedUpload() {
        List<GetExpectedUploadRes> collect = liveRepository.getExpectedUpload().stream()
                .map(m -> new GetExpectedUploadRes(m.getTitle(),m.getLecturer().getMajor(), m.getThumbnailUrl(),m.getLiveDate()))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<GetUploadedRes> getUploaded() {
        List<GetUploadedRes> collect = liveRepository.getUploaded().stream()
                .map(m -> new GetUploadedRes(m.getTitle(),m.getLecturer().getMajor(), m.getThumbnailUrl(),m.getLiveDate()))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public GetFilteredLiveRes getLiveOrderByViewNum(DateReq dateReq, String orderBy) {
        Long viewNum = liveRepository.getViewNum(dateReq.getStartDate(),dateReq.getEndDate());
        List<Live> liveList = null;

        if(orderBy.equals("asc")) liveList = liveRepository.getLiveOrderbyViewNumASC(dateReq.getStartDate(),dateReq.getEndDate());
        else liveList=liveRepository.getLiveOrderbyViewNumDESC(dateReq.getStartDate(),dateReq.getEndDate());

        List<GetFilteredLive> collect = liveList.stream()
                .map(m -> new GetFilteredLive(m.getTitle(), m.getThumbnailUrl(),m.getLiveDate(),m.getViewNum(), m.getApplicableNum(), m.getApplicantNum()))
                .collect(Collectors.toList());

        return new GetFilteredLiveRes(viewNum,collect);
    }

    @Override
    public GetFilteredLiveRes getLiveOrderByApplicantNum(DateReq dateReq) {
        Long viewNum = liveRepository.getViewNum(dateReq.getStartDate(),dateReq.getEndDate());

        List<GetFilteredLive> collect = liveRepository.getLiveOrderByApplicantNum(dateReq.getStartDate(),dateReq.getEndDate()).stream()
                .map(m -> new GetFilteredLive(m.getTitle(), m.getThumbnailUrl(),m.getLiveDate(),m.getViewNum(), m.getApplicableNum(), m.getApplicantNum()))
                .collect(Collectors.toList());
        return new GetFilteredLiveRes(viewNum,collect);
    }

}
