package com.education.service.file.impl;

import com.education.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author Дарья Лукьянова
 * Сервис-класс для добавления файла в файловое хранилище (FilePool)
 */

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        String name = multipartFile.getOriginalFilename();
        if (!multipartFile.isEmpty()) {
            try (BufferedOutputStream stream =
                         new BufferedOutputStream(new FileOutputStream(name + "-uploaded"))){
                byte[] bytes = multipartFile.getBytes();
                stream.write(bytes);
                return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }


    }
}
