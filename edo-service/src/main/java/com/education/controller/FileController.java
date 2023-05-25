package com.education.controller;

import com.education.model.dto.FilePoolDto;
import com.education.service.file.FileService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.logging.Level;


@RestController
@RequestMapping("api/service/file")
@RequiredArgsConstructor
@Log
@ApiModel("Контроллер сервиса файла")
public class FileController {
    @ApiModelProperty("Сервис для файла")
    private final FileService fileService;


    @ApiOperation("Добавить в хранилище файл")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "File successfully added."),
            @ApiResponse(code = 404, message = "Not found - The file was not found")
    })
    @PostMapping()
    public ResponseEntity<FilePoolDto> uploadFile(@RequestPart("file") MultipartFile multipartFile) {
        log.log(Level.INFO, "Получен запрос на добавление файла");
        FilePoolDto filePoolDto = fileService.saveFile(multipartFile);
        log.log(Level.INFO, "Файл успешно добавлен.");
        return new ResponseEntity<>(filePoolDto, HttpStatus.OK);
    }

    @ApiOperation("Получить file по UUID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "File was successfully found"),
            @ApiResponse(code = 404, message = "File was not found")})
    @GetMapping("/{UUID}")
    public ResponseEntity<?> getFileByUUID(@PathVariable("UUID") UUID uuid) {
        log.log(Level.INFO, "Получен запрос на поиск файла с UUID = {0}", uuid.toString());
        var file = fileService.getFileByUUID(uuid);
        log.log(file != null
                        ? Level.INFO
                        : Level.WARNING
                , "Результат поиска: {0}", file);
        return new ResponseEntity<>(file
                , file != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
