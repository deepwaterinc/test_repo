package com.education.controller;

import com.education.model.dto.NomenclatureDto;

import com.education.service.NomenclatureService;
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
@RequestMapping("api/rest/nomenclature")
@Log
@Log4j2
public class NomenclatureController {
    final private NomenclatureService nomenclatureService;

    @ApiOperation(value = "Получить список номенклатур из БД " +
            "по первым двум символам символам (?index=**")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Nomenclature not found")
    })
    @GetMapping("/search/")
    public ResponseEntity<List<NomenclatureDto>> dynamicSearchForNomenclature(
            @RequestParam("index") String index) {
        List<NomenclatureDto> nomenclature = nomenclatureService.findByIndex(index);
        if (nomenclature == null) {
            log.log(Level.WARNING, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(nomenclature, HttpStatus.OK);
    }
}
