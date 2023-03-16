package com.example.dashboardback.file.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.dashboardback.file.util.ImageExtension;
import com.example.dashboardback.file.exception.FileLoadFailedException;
import com.example.dashboardback.file.exception.FileSaveFailedException;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Override
    public String saveFile(MultipartFile file) {
        ImageExtension.validateImageExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
        final String key = UUID.randomUUID().toString();
        final String url="https://dashboard-myprofile.s3."+region+".amazonaws.com/"+key;
        this.amazonS3Client.putObject(this.putObjectRequest(file, key));
        return url;
    }

    private PutObjectRequest putObjectRequest(MultipartFile file, String filename) {
        try {
            return new PutObjectRequest(this.bucketName, filename, file.getInputStream(), this.objectMetadata(file));
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new FileSaveFailedException();
        }
    }

    private ObjectMetadata objectMetadata(MultipartFile file) {
        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(MediaType.IMAGE_JPEG_VALUE);
        return metadata;
    }

    @Override
    public byte[] getFile(String key) {
        try {
            return this.amazonS3Client.getObject(this.bucketName, key).getObjectContent().readAllBytes();
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new FileLoadFailedException();
        }
    }

    @Override
    public void deleteFile(String key) {
        this.amazonS3Client.deleteObject(this.bucketName, key);
    }

    @Override
    public void deleteAll(List<String> keys) {
        final DeleteObjectsRequest request = new DeleteObjectsRequest(this.bucketName);
        request.setKeys(keys.stream().map(DeleteObjectsRequest.KeyVersion::new).collect(Collectors.toList()));
        this.amazonS3Client.deleteObjects(request);
    }
}
