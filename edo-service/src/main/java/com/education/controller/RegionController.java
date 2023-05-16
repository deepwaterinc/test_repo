package com.education.controller;


import com.education.model.dto.RegionDto;
import com.education.service.region.RegionService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@ApiModel("Класс RegionController - RestController для взаимодействия с обращениями")
@RestController
@AllArgsConstructor
@RequestMapping("/api/service/region")
@Log4j2
public class RegionController {
    private final RegionService regionService;

    @ApiOperation(value = "Сохранение сущности в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Сущность сохранена"),
            @ApiResponse(code = 409, message = "Сущность не сохранена")
    })
    @PostMapping
    public ResponseEntity<RegionDto> saveRegion(@RequestBody RegionDto regionDto) {
        regionService.save(regionDto);
        log.info("Создан РЕГИОН");
        return new ResponseEntity<>(regionDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Получение сущности по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/byId/{id}")
    public ResponseEntity<RegionDto> findByIdRegion(@PathVariable Long id) {
        Optional<RegionDto> region = Optional.ofNullable(regionService.findById(id));
        if (region.isEmpty()) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(region.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение всех регионов")
    @GetMapping()
    public ResponseEntity<List<RegionDto>> findAll() {
        regionService.findAll();
        log.log(Level.INFO, "Получение всех регионов");
        return new ResponseEntity<>(regionService.findAll(), HttpStatus.OK);
    }

}