package com.education.controller;

import com.education.entity.Nomenclature;
import com.education.model.dto.NomenclatureDto;
import com.education.service.nomenclature.NomenclatureService;
import com.education.util.Mapper.impl.NomenclatureMapper;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@ApiModel("Nomenclature API")
@AllArgsConstructor
@RestController
@RequestMapping("api/repository/nomenclature")
@Log
@Log4j2
public class NomenclatureRestController {
    final private NomenclatureService nomenclatureService;
    final private NomenclatureMapper nomenclatureMapper;

    @ApiOperation(value = "Сохранить сущность в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Сущность сохранена")
    })
    @PostMapping("/")
    public ResponseEntity<NomenclatureDto> save(@RequestBody @ApiParam("Nomenclature") NomenclatureDto nomenclature) {
        log.log(Level.INFO, "Сохранил NomenclatureDto.class");
        return new ResponseEntity<>(nomenclatureService.save(nomenclature), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Получить номенклатуру по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Nomenclature not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<NomenclatureDto> findById(@PathVariable("id") Long id) {
        NomenclatureDto nomenclature = nomenclatureService.findById(id);

        if (nomenclature == null) {
            log.log(Level.WARNING, "not found NomenclatureDto with id = {0}", id);
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        }
        log.log(Level.INFO, "NomenclatureDto find: id = {0}", id);
        return new ResponseEntity<>(nomenclature, HttpStatus.OK);
    }

    @ApiOperation(value = "Получить список номенклатур по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Nomenclature not found")
    })
    @PostMapping("/findAll")
    public ResponseEntity<List<NomenclatureDto>> findAllById(@RequestBody List<Long> ids) {
        List<NomenclatureDto> nomenclatures = nomenclatureService.findAllById(ids);

        if (nomenclatures == null || nomenclatures.isEmpty()) {
            log.log(Level.WARNING, "List of NomenclatureDto not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "List of NomenclatureDto find");
        return new ResponseEntity<>(nomenclatures, HttpStatus.OK);
    }

    @ApiOperation(value = "Получить не заархивированную номенклатуру по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Nomenclature not found")
    })
    @GetMapping("/notArchived/{id}")
    public ResponseEntity<NomenclatureDto> findByIdNotArchived(@PathVariable("id") Long id) {
        NomenclatureDto nomenclature = nomenclatureService.findByIdNotArchived(id);

        if (nomenclature == null) {
            log.log(Level.WARNING, "not found not archived NomenclatureDto with id = {0}", id);
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        }
        log.log(Level.INFO, "not archived NomenclatureDto find: id = {0}", id);
        return new ResponseEntity<>(nomenclature, HttpStatus.OK);
    }

    @ApiOperation(value = "Получить список не заархивированных номенклатур по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Nomenclature not found")
    })
    @PostMapping("/notArchived")
    public ResponseEntity<List<NomenclatureDto>> findAllByIdNotArchived(@RequestBody List<Long> ids) {
        List<NomenclatureDto> nomenclatures = nomenclatureService.findAllByIdNotArchived(ids);

        if (nomenclatures == null || nomenclatures.isEmpty()) {
            log.log(Level.WARNING, "List of not archived NomenclatureDto not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "List of not archived NomenclatureDto find");
        return new ResponseEntity<>(nomenclatures, HttpStatus.OK);
    }

    @ApiOperation(value = "Поместить номенклатуру в архив")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved")
    })
    @PatchMapping("/archived/{id}")
    public ResponseEntity<Void> moveToArchive(@PathVariable("id") Long id) {
        nomenclatureService.moveToArchive(id);
        log.log(Level.INFO, "Nomenclature move to archive: id = {0}", id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Получить список номенклатур из БД " +
            "по первым двум символам символам (?index=**")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Nomenclature not found")
    })
    @GetMapping("/search/")
    public ResponseEntity<List<NomenclatureDto>> dynamicSearchForNomenclature(
            @RequestParam("index") String index) {
        List<Nomenclature> nomenclature = nomenclatureService.findByIndex(index);
        if (nomenclature == null) {
            log.log(Level.WARNING, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(nomenclatureMapper.toDto(nomenclature), HttpStatus.OK);
    }
}
