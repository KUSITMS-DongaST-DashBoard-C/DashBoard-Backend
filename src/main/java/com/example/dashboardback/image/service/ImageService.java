package com.example.dashboardback.image.service;


import com.example.dashboardback.image.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<Image> saveImages(List<MultipartFile> multipartFiles);
    Image saveImage(MultipartFile multipartFiles);
    Image getImage(Long imageId);
    boolean deleteImage(Long imageId);
}
