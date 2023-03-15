package com.education.controller;

import com.education.service.fileConvertion.FileConversionService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;


@RestController
@RequestMapping("api/service/file")
@RequiredArgsConstructor
@Log4j2
@ApiModel("Контроллер сервиса для конвертации файла")
public class FileConversionController {

    private final FileConversionService fileConversionService;

    @ApiOperation("Сконвертировать в pdf полученный из edo-rest файл")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Файл успешно сконвертирован в pdf"),
            @ApiResponse(code = 404, message = "Not found - The file was not found")
    })
    @PostMapping()
    public ResponseEntity<byte[]> convertFile(@RequestPart("file") MultipartFile multipartFile) {
        log.info("Получен запрос на конвертацию файла");
        byte[] array = fileConversionService.convertFile(multipartFile);
        log.info("Файл успешно сконвертирован");
        return new ResponseEntity<>(array, HttpStatus.OK);
    }
}
