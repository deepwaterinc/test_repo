package com.education.controller;

import com.education.converter.EmployeeConverters;
import com.education.entity.Employee;
import com.education.model.dto.EmployeeDto;
import com.education.service.employee.EmployeeService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/repository/employee")
@AllArgsConstructor
@Log
@ApiModel("Контроллер репозитория для сущности Employee")
public class EmployeeController {
    @ApiModelProperty("Сервис репозитория для сущности Employee")
    private final EmployeeService employeeService;

    @ApiOperation("Получить сущность Employee по id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Employee was successfully found"),
            @ApiResponse(code = 404, message = "Employee was not found")})
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
            @PathVariable("id") Long id
            , @RequestParam(name = "notArchivedOnly", defaultValue = "true") boolean notArchivedOnly) {
        log.log(Level.INFO, "Получен запрос на поиск сущности с id = {0}", id);
        EmployeeDto employeeDto = EmployeeConverters
                .entityToDtoConverter(notArchivedOnly
                        ? employeeService.findByIdNotArchived(id)
                        : employeeService.findById(id));
        log.log(employeeDto != null
                ? Level.INFO
                : Level.WARNING
                , "Результат поиска: {0}", employeeDto);
        return new ResponseEntity<>(employeeDto
                , employeeDto != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Получить несколько сущностей Employee по id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Employee was successfully found"),
            @ApiResponse(code = 404, message = "Employee was not found")})
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeById(@RequestParam("ids") List<Long> ids, @RequestParam(name = "notArchivedOnly", defaultValue = "true") boolean notArchivedOnly) {
        log.log(Level.INFO, "Получен запрос на поиск сущностей с id = {0}", ids);
        List<EmployeeDto> employeeDtos = (notArchivedOnly
                ? employeeService.findAllByIdNotArchived(ids)
                : employeeService.findAllById(ids))
                .stream()
                .map(EmployeeConverters::entityToDtoConverter).toList();
        log.log(!employeeDtos.isEmpty()
                        ? Level.INFO
                        : Level.WARNING
                , "Результат поиска: {0}", employeeDtos);
        return new ResponseEntity<>(employeeDtos
                , !employeeDtos.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Сохранить сущность employee")
    @ApiResponse(code = 201, message = "Employee was saved")
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        log.log(Level.INFO, "Получен запрос на сохранение сущности" );
        Employee employee = EmployeeConverters.dtoToEntityConverter(employeeDto);
        employeeService.save(employee);
        log.log(Level.INFO, "Сущность сохранена");
        return new ResponseEntity<>(EmployeeConverters.entityToDtoConverter(employee)
                , HttpStatus.CREATED);
    }

    @ApiOperation("Отправить сущность employee в архив")
    @ApiResponse(code = 200, message = "Employee was moved to archive")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> moveToArchiveEmployeeById(@PathVariable("id") Long id) {
        log.log(Level.INFO, "Получен запрос добавление сущности в архив");
        employeeService.moveToArchive(id);
        log.log(Level.INFO, "Сущность успешно добавлена в архив");
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
