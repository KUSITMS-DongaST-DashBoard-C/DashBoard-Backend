package com.example.dashboardback.user.dto;

import com.example.dashboardback.image.entity.Image;
import com.example.dashboardback.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class UserMapperSupport {
    private final ImageService imageService;

    @Named("saveImage")
    public Image saveImage (MultipartFile file) throws IOException {
        return this.imageService.saveImage(file);
    }

    @Named("getImageKey")
    public String getImageKey(Image image) {
        return image.getImageUrl();
    }
}
