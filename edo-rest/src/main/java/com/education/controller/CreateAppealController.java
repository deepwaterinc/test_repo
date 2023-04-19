package com.education.controller;

import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.service.Appeal.CreatingAppealService;
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

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/rest/appeal")
public class CreateAppealController {

    private final CreatingAppealService service;


    @ApiOperation(value = "Сохранение обращения")
    @PostMapping
    public ResponseEntity<AppealDto> createNewAppeal(@RequestBody AppealDto appeal) {
        AppealDto appealAfter = service.createAppeal(appeal);
        return new ResponseEntity<>(appealAfter, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Получение сущностей Appeal для Employee creator (?first=1&amount=1)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })

    @GetMapping(value = "/appealsByEmployee/")
    public ResponseEntity<List<AppealAbbreviatedDto>> findByIdEmployee(@RequestParam("first") Long first,
                                                                       @RequestParam("amount") Long amount) {
        List<AppealAbbreviatedDto> appeal = service.findAllByIdEmployee(first, amount);
        if (appeal == null && appeal.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(appeal, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущности Appeal по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/appealById/{id}")
    public ResponseEntity<AppealDto> findById(@ApiParam("id") @PathVariable Long id) {
        AppealDto appeal = service.findById(id);
        if (appeal == null) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(appeal, HttpStatus.OK);
    }
}