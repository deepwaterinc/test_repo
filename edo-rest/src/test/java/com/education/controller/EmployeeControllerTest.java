package com.education.controller;

import com.education.EdoRestApplication;
import com.education.model.dto.EmployeeDto;
import com.education.service.employee.EmployeeService;
import org.hamcrest.CoreMatchers;
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

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Интеграционный тест поиска Employee по ФИО.
 * Для запуска требуется запустить следующие модули:
 * edo-cloud-server
 * edo-service
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EdoRestApplication.class)
@AutoConfigureMockMvc
class EmployeeControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private final String BASE_URL = "/api/rest/employee";

//    @Test
//    public void userSearchTest() throws Exception {
//        given(employeeService.findAllByLastNameLikeOrderByLastName(ArgumentMatchers.anyString())).willAnswer(args -> List.of(getEmployeeDto()));
//        String fio = "Рейн Дмитрий Константинович";
//        mockMvc.perform(get(BASE_URL + "/byFIO/").param("fio", fio))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].fioNominative", CoreMatchers.is(fio)));
//    }
//
//    @Test
//    public void userSearchLessSymbolsTest() throws Exception {
//        String fio = "Ре";
//        mockMvc.perform(get(BASE_URL + "/byFIO/").param("fio", fio))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", CoreMatchers.is(List.of())));
//    }
//
//    @Test
//    public void userSearchEnglishSymbolsTest() throws Exception {
//        String fio = "Есть латинский симвoл";
//        mockMvc.perform(get(BASE_URL + "/byFIO/").param("fio", fio))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", CoreMatchers.is(List.of())));
//    }
//
//    @Test
//    public void userSearchNoArgsTest() throws Exception {
//        String fio = "";
//        mockMvc.perform(get(BASE_URL + "/byFIO/").param("fio", fio))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", CoreMatchers.is(List.of())));
//    }
//
//    @Test
//    public void userSearchUpperLowerCaseTest() throws Exception {
//        String fio = "рейн ДмитРий Константинович";
//        mockMvc.perform(get(BASE_URL + "/byFIO/").param("fio", fio))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", CoreMatchers.is(List.of())));
//    }
//
//
//    private EmployeeDto getEmployeeDto() {
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setAddress("Address");
//        employeeDto.setBirthDate(ZonedDateTime.now());
//        employeeDto.setFirstName("Дмитрий");
//        employeeDto.setMiddleName("Константинович");
//        employeeDto.setLastName("Рейн");
//        employeeDto.setFioDative("Рейну Дмитрию Константиновичу");
//        employeeDto.setFioGenitive("Рейна Дмитрия Константиновича");
//        employeeDto.setFioNominative(employeeDto.getLastName() + " " + employeeDto.getFirstName() + " " + employeeDto.getMiddleName());
//        employeeDto.setExternalId(UUID.randomUUID().toString());
//        employeeDto.setUsername("reyn_d");
//        employeeDto.setPhone("+79776020338");
//        employeeDto.setWorkEmail("room083@gmail.com");
//        employeeDto.setWorkPhone("+79786039037");
//        return employeeDto;
//    }
}