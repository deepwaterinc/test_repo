package com.education.service;

import com.education.model.dto.FileDto;

import java.io.InputStream;

public interface MinioService {
    public FileDto uploadFile(FileDto request);
    public InputStream getObject(String filename);

}
