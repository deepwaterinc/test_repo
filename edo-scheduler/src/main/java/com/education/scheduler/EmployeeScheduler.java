package com.education.scheduler;

import com.education.mapper.EmployeeDtoMapper;
import com.education.model.dto.DepartmentDto;
import com.education.model.dto.EmployeeDto;
import com.education.DTO.FullEmployeeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static com.education.model.constant.RabbitConstant.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeScheduler {
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final EmployeeDtoMapper employeeDtoMapper;

    //Метод отправляет данные раз в час
    @Scheduled(cron = "${scheduler.cron}")
    public void fetchAndSendEmployeeData() {
         if (!Boolean.parseBoolean(System.getProperty("scheduler.enabled"))) {
             return;
         }

        ResponseEntity<String> response = restTemplate.getForEntity("http://xn--d1ab2a.space/mock/employees", String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String jsonData = response.getBody();
            if (jsonData != null) {
                try {
                    // Directly convert the JSON data to a list of FullEmployeeDto
                    TypeReference<List<FullEmployeeDto>> typeRef = new TypeReference<>() {};
                    List<FullEmployeeDto> fullEmployeeDtos = objectMapper.readValue(jsonData, typeRef);

                    // Create lists for EmployeeDto and CompanyDto
                    List<EmployeeDto> employeeDtoList = new ArrayList<>();
                    Set<DepartmentDto> departmentDtoSet = new HashSet<>();

                    for (FullEmployeeDto fullEmployeeDto : fullEmployeeDtos) {
                        // Use your mapper to convert FullEmployeeDto to EmployeeDto and DepartmentDto
                        EmployeeDto employeeDto = employeeDtoMapper.toEmployeeDto(fullEmployeeDto);
                        DepartmentDto departmentDto = employeeDtoMapper.toDepartmentDto(fullEmployeeDto);

                        // Add them to the Collection
                        employeeDtoList.add(employeeDto);
                        departmentDtoSet.add(departmentDto);
                    }

                    // Send the lists to the queue
                    rabbitTemplate.convertAndSend(exchange, ROUTING_KEY_SCHEDULER, employeeDtoList);
                    rabbitTemplate.convertAndSend(exchange, ROUTING_KEY_SCHEDULER, departmentDtoSet);
                    System.out.println("sent");
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


