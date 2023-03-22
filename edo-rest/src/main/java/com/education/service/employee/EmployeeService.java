package com.education.service.employee;

import com.education.model.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAllByLastNameLikeOrderByLastName(String fio);
}
