package com.example.dashboardback.contents.original.service;

import com.example.dashboardback.contents.original.dto.OrigianlDto.UploadInfoResponse;
import com.example.dashboardback.contents.original.entity.Original;
import com.example.dashboardback.contents.original.repository.OriginalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OriginalServiceImpl implements OriginalService{

    private final OriginalRepository originalRepository;

    @Override
    public void createOriginal() {
        Original original=new Original();
        original.setSeriesName("똑같은 형태의 당뇨병을 가진 사람은 없다!");
        original.setEpisodeNum(3);
        original.setReviewNum(0);
        original.setRating(0.0);
        original.setLikeNum(0);
        original.setCommentNum(0);
        original.setThumbnailUrl("https://dashboard-myprofile.s3.ap-northeast-2.amazonaws.com/original6.jpg");
        original.setTitle("똑같은 형태의 당뇨병을 가진 사람은 없다!");
        original.setDescription("똑같은 형태의 당뇨병을 가진 사람은 없다!");
        original.setDeadlineDate(LocalDateTime.of(2023,05,05,23,59));
        original.setMajor("내과");
        original.setHits(0L);
        original.setIsDeleted(false);
        original.setIsUploaded(false);
        original.setUploadDate(LocalDateTime.of(2023,04,04,00,00));
        originalRepository.save(original);
    }

    @Override
    public List<UploadInfoResponse> getUpload() {
        return originalRepository.getUpload();

    }
}
