package com.education.service.impl;

import com.education.model.dto.FileDto;
import com.education.service.MinioService;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
public class MinioServiceImpl implements MinioService {
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    @Autowired
    public MinioServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public FileDto uploadFile(FileDto request) {
        UUID uuid = UUID.randomUUID();
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(uuid.toString())
                    .stream(request.getFile().getInputStream(), request.getFile().getSize(), -1)
                    .build());

            return FileDto.builder()
                    .size(request.getFile().getSize())
                    .filename(request.getFile().getOriginalFilename())
                    .uuidName(uuid.toString())
                    .build();
        } catch (Exception e) {
            log.error("Happened error when upload file: ", e);
            throw new RuntimeException(e);
        }
    }

    public InputStream getObject(String filename) {
        InputStream stream;
        try {
            stream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filename)
                    .build());
        } catch (Exception e) {
            log.error("Happened error when get list objects from minio: ", e);
            throw new RuntimeException(e);
        }

        return stream;
    }

}

