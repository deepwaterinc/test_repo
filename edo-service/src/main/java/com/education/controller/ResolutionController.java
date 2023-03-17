package com.education.controller;

import com.education.model.dto.ResolutionDto;
import com.education.service.resolution.ResolutionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation("ResolutionDto API")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("api/service/resolution")
public class ResolutionController {

    final private ResolutionService resolutionService;

    @ApiOperation(value = "Сохранение сущности в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Сущность сохранена"),
            @ApiResponse(code = 409, message = "Сущность не сохранена")
    })
    @PostMapping
    public ResponseEntity<ResolutionDto> saveResolution(@ApiParam("resolutionDto") @RequestBody ResolutionDto resolutionDto) {
        ResolutionDto resolutionDtoAfter = resolutionService.save(resolutionDto);
        if (resolutionDtoAfter.getId() != null) {
            log.log(Level.INFO, "Сущность сохранена или обновлена");
            return new ResponseEntity<>(resolutionDtoAfter, HttpStatus.CREATED);
        }
        log.log(Level.WARN, "Сущность не сохранена и не обновлена");
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ApiOperation(value = "Обновление даты архивации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность изменена"),
    })
    @PutMapping("/toArchive/{id}")
    public ResponseEntity<ResolutionDto> moveToArchiveResolution(@ApiParam("id") @PathVariable Long id) {
        resolutionService.moveToArchive(id);
        log.log(Level.INFO, "Дата архивации обновлена");
        return new ResponseEntity<>(resolutionService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущности по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/byId/{id}")
    public ResponseEntity<ResolutionDto> findByIdResolution(@ApiParam("id") @PathVariable Long id) {
        ResolutionDto resolution = resolutionService.findById(id);
        if (resolution == null) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(resolution, HttpStatus.OK);

    }

    @ApiOperation(value = "Получение сущностей по списку id (/1, 2, 3)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })
    @GetMapping(value = "/allById/{ids}")
    public ResponseEntity<List<ResolutionDto>> findAllByIdResolution(@ApiParam("ids") @PathVariable List<Long> ids) {
        List<ResolutionDto> resolutionDto = resolutionService.findAllById(ids);
        if (resolutionDto == null && resolutionDto.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/notArchived/{id}")
    public ResponseEntity<ResolutionDto> findByIdNotArchivedResolution(@ApiParam("id") @PathVariable Long id) {
        ResolutionDto resolutionDto = resolutionService.findByIdNotArchived(id);
        if (resolutionDto == null) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по списку id (/1, 2) ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })

    @GetMapping(value = "/allNotArchived/{ids}")
    public ResponseEntity<List<ResolutionDto>> findAllByIdNotArchivedResolution(@ApiParam("ids") @PathVariable List<Long> ids) {
        List<ResolutionDto> resolutionDto = resolutionService.findAllByIdNotArchived(ids);
        if (resolutionDto == null && resolutionDto.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }
}