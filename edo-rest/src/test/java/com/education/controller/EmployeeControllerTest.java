package com.education.controller;

import com.education.EdoRestApplication;
import com.education.service.employee.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EdoRestApplication.class)
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void userSearch() throws Exception {
        given(employeeService.findAllByLastNameLikeOrderByLastName(ArgumentMatchers.anyString())).willAnswer(invocation -> invocation.getArguments())
    }
}