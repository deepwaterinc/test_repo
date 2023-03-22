package com.education.service.question;

import com.education.model.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    QuestionDto save(QuestionDto question);

    void moveToArchive(Long id);

    QuestionDto findById(Long id);

    List<QuestionDto> findAllById(List<Long> ids);

    QuestionDto findByIdNotArchived(Long id);

    List<QuestionDto> findAllByIdNotArchived(List<Long> ids);

}
