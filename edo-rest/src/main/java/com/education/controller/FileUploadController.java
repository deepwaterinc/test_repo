package com.education.controller;

import com.education.client.FileRestTemplateClient;
import com.education.service.file.FileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@ApiOperation("Upload file API")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rest/file")
public class FileUploadController {

    final private FileService fileService;

    final private FileRestTemplateClient restTemplateClient;

    @ApiOperation("Скачать файл")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Файл успешно сохранен"),
            @ApiResponse(code = 404, message = "Файл не найден")
    })
    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile multipartFile) {
        log.info("Получен запрос на скачивание файла");
        if (multipartFile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.NOT_FOUND);
        }
        fileService.uploadFile(multipartFile);
        restTemplateClient.convertFile(multipartFile);
        log.info("Файл успешно сохранен");
        return new ResponseEntity(multipartFile.getOriginalFilename(), HttpStatus.OK);


    }

}
