package com.education.controller;

import com.education.model.dto.DeadlineDto;
import com.education.service.deadline.DeadlineService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation("DeadlineDto API")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("api/service/deadline")
public class DeadlineController {

    private final DeadlineService deadlineService;

    @Operation(summary = "Сохраняет дедлайн")
    @PostMapping("/")
    public ResponseEntity<DeadlineDto> saveDeadline(@RequestBody DeadlineDto deadlineDto) {
        log.info("Отправляю DeadlineDto. Дата дедлайна: {0}", deadlineDto.getDate());
        return new ResponseEntity<>(deadlineService.save(deadlineDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Изменяет дедлайн и указывает причину")
    @PutMapping("/")
    public ResponseEntity<DeadlineDto> updateDeadline(@RequestBody DeadlineDto deadlineDto) {
        log.info("Изменяю DeadlineDto. Новая дата дедлайна: {0} Причина: {1}", new Object[] {deadlineDto.getDate(), deadlineDto.getComment()});
        return new ResponseEntity<>(deadlineService.update(deadlineDto), HttpStatus.OK);
    }

    @Operation(summary = "Удаляет дедлайн по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeadline(@PathVariable("id") Long id) {
        log.info("Получен запрос на удаление дедлайна с id = {0}", id);
        deadlineService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Выдает дедлайн по id")
    @GetMapping("/{id}")
    public ResponseEntity<DeadlineDto> getDeadlineById(@PathVariable("id") Long id) {
        log.info("Получен запрос на выдачу дедлайна с id = {0}", id);
        return new ResponseEntity<>(deadlineService.findById(id), HttpStatus.OK);
    }

    @ApiOperation("Поиск списка дедлайнов по их id")
    @PostMapping("/all")
    public ResponseEntity<List<DeadlineDto>> getAllById(@RequestBody List<Long> ids) {
        log.info("Получен запрос на выдачу дедлайнов для набора id = {0}", ids);
        return new ResponseEntity<>(deadlineService.findAllById(ids), HttpStatus.OK);
    }

}
