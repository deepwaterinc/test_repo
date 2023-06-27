package com.education.controller;

import com.education.model.dto.ApprovalSheetDto;
import com.education.service.approvalsheet.ApprovalSheetService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiModel("Approval Sheet API")
@Log4j2
@RestController
@RequestMapping("api/service/approval")
@RequiredArgsConstructor
public class ApprovalSheetController {

    private final ApprovalSheetService approvalSheetService;

    @ApiOperation(value = "Сохранение сущности в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully saved"),
            @ApiResponse(code = 409, message = "Saving failed")
    })
    @PostMapping
    public ResponseEntity<ApprovalSheetDto> save(@RequestBody ApprovalSheetDto approvalSheetDto) {
        ApprovalSheetDto savedApprovalSheetDto = approvalSheetService.save(approvalSheetDto);
        log.info("Сохранен Лист согласования с ID {}", savedApprovalSheetDto.getId());
        return new ResponseEntity<>(savedApprovalSheetDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Обновление даты архивации Листа согласования")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
    })
    @PutMapping("/archive/{id}")
    public ResponseEntity<ApprovalSheetDto> moveToArchive(@PathVariable Long id) {
        approvalSheetService.moveToArchive(id);
        log.log(Level.INFO, "Дата архивации Листа согласования обновлена");
        return new ResponseEntity<>(approvalSheetService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение Листа согласования по ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApprovalSheetDto> getById(@PathVariable Long id) {
        ApprovalSheetDto approvalSheetDto = approvalSheetService.findById(id);
        if (approvalSheetDto == null) {
            log.log(Level.WARN, "Лист согласования не найден");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Лист согласования найден");
        return new ResponseEntity<>(approvalSheetDto, HttpStatus.OK);
    }

    @ApiOperation("Поиск списка Листов согласования по их ID")
    @GetMapping
    public List<ApprovalSheetDto> findAllById(@RequestParam("ids") List<Long> ids) {
        log.info("Получен запрос на получение листов согласования для набора id = {}", ids);
        return approvalSheetService.findAllById(ids);
    }

    @ApiOperation(value = "Получение Листа согласования без даты архивации по ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @GetMapping(value = "/notArchived/{id}")
    public ResponseEntity<ApprovalSheetDto> getByIdNotArchived(@PathVariable Long id) {
        ApprovalSheetDto approvalSheetDto = approvalSheetService.findByIdNotArchived(id);
        if (approvalSheetDto == null) {
            log.log(Level.WARN, "Лист согласования не найден");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Лист согласования найден");
        return new ResponseEntity<>(approvalSheetDto, HttpStatus.OK);
    }

    @Operation(summary = "Выдает неархивные листы согласования по списку id")
    @GetMapping("/notArchived")
    public ResponseEntity<List<ApprovalSheetDto>> findAllByIdNotArchived(@RequestParam(value = "ids", required = false) List<Long> ids) {
        log.info("Получен запрос на получение неархивных листов согласования для набора id = {}", ids);
        return new ResponseEntity<>(approvalSheetService.findAllByIdNotArchived(ids), HttpStatus.OK);
    }
}
