package com.education.service.appeal;

import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;

import java.util.List;

public interface AppealService {
    AppealDto save(AppealDto appeal);

    void moveToArchive(Long id);

    AppealDto findById(Long id);

    List<AppealDto> findAllById(Iterable<Long> ids);

    AppealDto findByIdNotArchived(Long id);

    List<AppealDto> findAllByIdNotArchived(Iterable<Long> ids);

    List<AppealAbbreviatedDto> findAllByIdEmployee(Long first, Long amount);
}