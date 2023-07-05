package com.education.controller;

import com.education.model.dto.ApprovalSheetDto;
import com.education.service.approvalsheet.ApprovalSheetService;
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
@RequestMapping("api/rest/approval")
public class ApprovalSheetController {

    private final ApprovalSheetService service;

    @ApiOperation(value = "Сохранение листа согласования")
    @PostMapping
    public ResponseEntity<ApprovalSheetDto> createNewApprovalSheet(@RequestBody ApprovalSheetDto approvalSheetDto) {
        ApprovalSheetDto approvalSheetDtoAfter = service.save(approvalSheetDto);
        return new ResponseEntity<>(approvalSheetDtoAfter, HttpStatus.CREATED);
    }

}
