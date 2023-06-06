package com.education.service;

import java.io.InputStream;
import java.util.UUID;


public interface MinioService {
     UUID uploadFile(byte[] request);
    InputStream getObject(String filename);
}
