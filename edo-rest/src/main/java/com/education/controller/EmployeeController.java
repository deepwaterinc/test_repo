package com.education.controller;

import com.education.model.dto.EmployeeDto;
import com.education.service.employee.EmployeeService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;

@ApiModel("Employee API")
@AllArgsConstructor
@RestController
@RequestMapping("api/rest/employee")
@Log
@Log4j2
public class EmployeeController {
    private final EmployeeService service;

    @ApiOperation(value = "Поиск пользователя по ФИО")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee was successfully found"),
            @ApiResponse(code = 404, message = "Employee was not found")})
    @GetMapping("/byFIO/")
    public ResponseEntity<List<EmployeeDto>> userSearch(@RequestParam("fio") String fio) {
        log.log(Level.INFO, "Получен запрос на поиск сущностей {0}", fio);
        List<EmployeeDto> dtos = service.findAllByLastNameLikeOrderByLastName(fio);
        log.log(!dtos.isEmpty()
                        ? Level.INFO
                        : Level.WARNING
                , "Результат поиска сущностей: {0}", dtos);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
