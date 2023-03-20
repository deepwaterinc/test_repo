package com.education.service.employee;


import com.education.model.dto.EmployeeDto;

import java.util.List;


public interface EmployeeRestTemplateService {
    EmployeeDto findById(Long id, boolean notArchivedOnly);

    List<EmployeeDto> findAllById(List<Long> ids, boolean notArchivedOnly);

    EmployeeDto save(EmployeeDto emp);

    void moveToArchive(Long id);
    public List<EmployeeDto> findAllByLastNameLikeOrderByLastName(String fio);
}
