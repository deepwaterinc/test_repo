package com.education.service.file;

import com.education.model.dto.FilePoolDto;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    FilePoolDto uploadFile(MultipartFile multipartFile);
}
