package com.education.service.deadline;

import com.education.model.dto.DeadlineDto;
import com.education.model.dto.QuestionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeadlineService {

    DeadlineDto save(DeadlineDto deadlineDto);

    DeadlineDto update(DeadlineDto deadlineDto);

    void delete (Long id);

    DeadlineDto findById(Long id);

    List<DeadlineDto> findAllById(List <Long> ids);

}
