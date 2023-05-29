package com.education.controller;


import com.education.entity.Region;
import com.education.model.dto.RegionDto;
import com.education.service.region.RegionService;
import com.education.util.Mapper.impl.RegionMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@ApiOperation("Region API")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("api/repository/region")
public class RegionController {
    private final RegionService regionService;
    private final RegionMapper mapper;

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
        Optional<Region> region = regionService.findById(id);
        if (region.isEmpty()) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(mapper.toDto(region.get()), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение всех регионов")
    @GetMapping()
    public ResponseEntity<List<Region>> findAll() {
        regionService.findAll();
        log.log(Level.INFO, "Получение всех регионов");
        return new ResponseEntity<>(regionService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/byId/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена и удалена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    public ResponseEntity<HttpStatus> deleteByIdRegion(@PathVariable Long id) {
        try {
            regionService.delete(id);
            log.log(Level.INFO, "Регион с id - ${id} - удален. ");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.log(Level.WARN, "Попытка удалить регион с несуществующим id - ${id}  ");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

