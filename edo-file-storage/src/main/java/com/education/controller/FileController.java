package com.education.controller;

import com.education.model.dto.FileDto;
import com.education.service.MinioService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/file-storage")
public class FileController {

    @ApiModelProperty("сервис для контроллера")
    private final MinioService minioService;


    @ApiOperation(value = "Загрузка файла в MinIO", notes = "Сохраняет файл без расширения с UUID вместо названия")
    @PostMapping(value = "/upload")
    public ResponseEntity<FileDto> upload(@ModelAttribute FileDto request) {
        return ResponseEntity.ok().body(minioService.uploadFile(request));
    }

    @ApiOperation(value = "Загрузка файла из MinIO", notes = "Возвращает файл по названию(UUID)")
    @GetMapping(value = "/download/{uuid}")
    public ResponseEntity<?> getFile(@PathVariable String uuid) throws IOException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(IOUtils.toByteArray(minioService.getObject(uuid)));
    }

}
