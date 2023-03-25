package com.education.testControllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/rest/appeal")
public class TestAppealController {

    private final SaveAppealServiceImpl appealService;

    @GetMapping("/tSave")
    public ResponseEntity<String> tSave() {
        String resp = appealService.callEdoServiceSaveT();
        return ResponseEntity.ok(resp);
    }

}
