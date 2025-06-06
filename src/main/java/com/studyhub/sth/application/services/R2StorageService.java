package com.studyhub.sth.application.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.IOException;
import java.util.UUID;

@Service
public class R2StorageService {

    private final S3Client s3Client;

    @Value("${r2.base-url:}")
    private String BASE_URL;

    @Value("${r2.bucket-name:}")
    private String bucketName;

    public R2StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        byte[] fileBytes = file.getBytes();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(this.bucketName)
                .key(fileName)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileBytes)); // Usar bytes diretamente

        return BASE_URL + "/" + fileName;
    }

    public String uploadFile(byte[] file, String fileName) throws IOException {
        String finalFileName = UUID.randomUUID() + "_" + fileName;


        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(this.bucketName)
                .key(fileName)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file)); // Usar bytes diretamente

        return BASE_URL + "/" + fileName;
    }
}

