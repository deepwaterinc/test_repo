package com.education.service;

import com.education.dto.FileDto;

import java.io.InputStream;
import java.util.List;

public interface MinioService {
    public List<FileDto> getListObjects();
    public FileDto uploadFile(FileDto request);
    public InputStream getObject(String filename);

}
