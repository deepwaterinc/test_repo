package com.education.service.file;


import org.springframework.web.multipart.MultipartFile;


public interface FileService {

    String uploadFile(MultipartFile multipartFile);
}
