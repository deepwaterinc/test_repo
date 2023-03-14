package com.education.service.employee.impl;



import com.education.client.EmployeeRestTemplateClient;
import com.education.model.dto.EmployeeDto;
import com.education.service.employee.EmployeeRestTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class EmployeeRestTemplateServiceImpl implements EmployeeRestTemplateService {
    private final EmployeeRestTemplateClient employeeClient;


    @Override
    public EmployeeDto findById(Long id, boolean notArchivedOnly) {
        return employeeClient.getEmployeeById(id, notArchivedOnly);
    }

    @Override
    public List<EmployeeDto> findAllById(List<Long> ids, boolean notArchivedOnly) {
        return employeeClient.getAllEmployeeById(ids, notArchivedOnly);
    }

    @Override
    public EmployeeDto save(EmployeeDto emp) {
        return employeeClient.saveEmployee(emp);
    }

    @Override
    public void moveToArchive(Long id) {
        employeeClient.moveEmployeeToArchive(id);
    }

    @Override
    public List<EmployeeDto> findAllByLastNameLikeOrderByLastName(String fio) {
        return fio.length() < 3 ? null : employeeClient.findAllByLastNameLikeOrderByLastName(fio);
    }

}


