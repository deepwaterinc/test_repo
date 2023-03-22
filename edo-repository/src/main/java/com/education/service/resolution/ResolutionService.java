package com.education.service.resolution;

import com.education.entity.Resolution;

import java.util.List;

public interface ResolutionService {
    Resolution save(Resolution resolution);
    void moveToArchive(Long id);
    Resolution findById(Long id);
    List<Resolution> findAllById(Iterable<Long> ids);
    Resolution findByIdNotArchived(Long id);
    List<Resolution> findAllByIdNotArchived(Iterable<Long> ids);
}
