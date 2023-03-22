package com.example.dashboardback.contents.life.service;

import com.example.dashboardback.contents.life.dto.Res.GetExpectedUploadRes;
import com.example.dashboardback.contents.life.dto.Res.GetUploadedRes;
import com.example.dashboardback.contents.life.repository.LifeRepository;
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
}
