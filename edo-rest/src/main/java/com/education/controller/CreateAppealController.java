package com.education.controller;

import com.education.model.dto.AppealDto;
import com.education.service.Appeal.CreatingAppealService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}