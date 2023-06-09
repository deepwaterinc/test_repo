package com.education.service.deadline;

import com.education.model.dto.DeadlineDto;

import java.util.List;

public interface DeadlineService {

    DeadlineDto save(DeadlineDto deadlineDto);

    DeadlineDto update(DeadlineDto deadlineDto);

    void delete (Long id);

    DeadlineDto findById(Long id);

    List<DeadlineDto> findAllById(List <Long> ids);
}
