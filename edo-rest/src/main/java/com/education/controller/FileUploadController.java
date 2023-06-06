package com.education.controller;

import com.education.model.dto.FilePoolDto;
import com.education.service.file.FileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Log
@ApiOperation("Upload file API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rest/file")
public class FileUploadController {
    final private FileService fileService;

    @ApiOperation("Скачать файл")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Файл успешно сохранен"),
            @ApiResponse(code = 404, message = "Файл не найден")
    })
    @PostMapping
    public ResponseEntity<FilePoolDto> uploadFile(@RequestPart("file") MultipartFile multipartFile) {
        log.info("Получен запрос на закачивание файла");
        if (multipartFile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(fileService.uploadFile(multipartFile));
    }
}
