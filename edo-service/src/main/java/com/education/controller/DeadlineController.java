package com.education.controller;

import com.education.model.dto.DeadlineDto;
import com.education.service.deadline.DeadlineService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@ApiOperation("DeadlineDto API")
@Log
@RequiredArgsConstructor
@RestController
@RequestMapping("api/service/deadline")
public class DeadlineController {

    private final DeadlineService deadlineService;

    @Operation(summary = "Сохраняет дедлайн")
    @PostMapping("/")
    public ResponseEntity<DeadlineDto> saveDeadline(@RequestBody DeadlineDto deadlineDto) {
        log.log(Level.INFO, "Отправляю DeadlineDto на сохранение. Дата дедлайна: {0}", deadlineDto.getDate());
        return new ResponseEntity<>(deadlineService.save(deadlineDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Изменяет дедлайн и указывает причину")
    @PutMapping("/")
    public ResponseEntity<DeadlineDto> updateDeadline(@RequestBody DeadlineDto deadlineDto) {
        log.log(Level.INFO, "Изменяю DeadlineDto. Новая дата дедлайна: {0} Причина: {1}", new Object[]{deadlineDto.getDate(), deadlineDto.getComment()});
        return new ResponseEntity<>(deadlineService.update(deadlineDto), HttpStatus.OK);
    }

    @Operation(summary = "Удаляет дедлайн по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeadline(@PathVariable("id") Long id) {
        log.log(Level.INFO, "Получен запрос на удаление дедлайна с id = {0}", id);
        deadlineService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Выдает дедлайн по id")
    @GetMapping("/{id}")
    public ResponseEntity<DeadlineDto> getDeadlineById(@PathVariable("id") Long id) {
        log.log(Level.INFO, "Получен запрос на выдачу дедлайна с id = {0}", id);
        return new ResponseEntity<>(deadlineService.findById(id), HttpStatus.OK);
    }

    @ApiOperation("Поиск списка дедлайнов по их id")
    @GetMapping
    public List<DeadlineDto> findAllById(@RequestParam("ids") List<Long> ids) {
        log.log(Level.INFO, "Получен запрос на выдачу дедлайнов для набора id = {0}", ids);
        return deadlineService.findAllById(ids);
    }

}
