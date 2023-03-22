package com.education.service.fileConvertion;

import org.jvnet.hk2.annotations.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileConversionService {

    byte[] convertFile(MultipartFile multipartFile);
}
