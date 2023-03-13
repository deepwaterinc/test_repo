package com.education.controller;

import com.education.entity.Appeal;
import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.service.appeal.AppealService;
import com.education.util.Mapper.impl.AppealAbbreviatedMapper;
import com.education.util.Mapper.impl.AppealMapper;
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

@ApiOperation("Appeal API")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("api/repository/appeal")
public class AppealController {
    final private AppealService appealService;

    final private AppealMapper mapper;
    final private AppealAbbreviatedMapper AppealAbbreviatedMapper;


    @ApiOperation(value = "Сохранение сущности в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Сущность сохранена"),
            @ApiResponse(code = 409, message = "Сущность не сохранена")
    })
    @PostMapping
    public ResponseEntity<AppealDto> saveAppeal(@RequestBody AppealDto appealDto) {

        AppealDto appealAfter = mapper.toDto(appealService.save(mapper.toEntity(appealDto)));
        log.info("Создано ОБРАЩЕНИЕ с id {}", appealAfter.getId());
        return new ResponseEntity<>(appealAfter, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Обновление даты архивации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность изменена"),
    })
    @PutMapping("/toArchive/{id}")
    public ResponseEntity<AppealDto> moveToArchiveAppeal(@PathVariable Long id) {
        appealService.moveToArchive(id);
        log.log(Level.INFO, "Дата архивации обновлена");
        return new ResponseEntity<>(mapper.toDto(appealService.findById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущности по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/byId/{id}")
    public ResponseEntity<AppealDto> findByIdAppeal(@PathVariable Long id) {
        Appeal appeal = appealService.findById(id);
        if (appeal == null) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(mapper.toDto(appeal), HttpStatus.OK);

    }

    @ApiOperation(value = "Получение сущностей по списку id (/1, 2, 3)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })
    @GetMapping(value = "/allById/{ids}")
    public ResponseEntity<List<AppealDto>> findAllByIdAppeal(@PathVariable List<Long> ids) {
        List<Appeal> appeal = appealService.findAllById(ids);
        if (appeal == null && appeal.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(mapper.toDto(appeal), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/notArchived/{id}")
    public ResponseEntity<AppealDto> findByIdNotArchivedAppeal(@PathVariable Long id) {
        Appeal appeal = appealService.findByIdNotArchived(id);
        if (appeal == null) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(mapper.toDto(appeal), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по списку id (/1, 2) ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })
    @GetMapping(value = "/allNotArchived/{ids}")
    public ResponseEntity<List<AppealDto>> findAllByIdNotArchivedAppeal(@PathVariable List<Long> ids) {
        List<Appeal> appeal = appealService.findAllByIdNotArchived(ids);
        if (appeal == null && appeal.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(mapper.toDto(appeal), HttpStatus.OK);
    }

    /**
     * В качестве id объекта Principal используется заглушка (idPrincipal = 1L)
     */
    @ApiOperation(value = "Получение сущностей Appeal для Employee creator (?first=1&amount=1)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/appealsByEmployee/")
    public ResponseEntity<List<AppealAbbreviatedDto>> findByIdEmployee(@RequestParam("first") Long first,
                                                                       @RequestParam("amount") Long amount) {
        Long idPrincipal = 1L;
        List<Appeal> appeal = appealService.findAllByIdEmployee(idPrincipal, first, amount);
        if (appeal == null && appeal.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(AppealAbbreviatedMapper.toDto(appeal), HttpStatus.OK);
    }
}
