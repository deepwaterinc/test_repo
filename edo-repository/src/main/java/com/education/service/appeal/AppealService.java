package com.education.service.appeal;


import com.education.entity.Appeal;

import java.util.List;

public interface AppealService {
    Appeal save(Appeal appeal);

    void moveToArchive(Long id);

    Appeal findById(Long id);

    List<Appeal> findAllById(Iterable<Long> ids);

    Appeal findByIdNotArchived(Long id);

    List<Appeal> findAllByIdNotArchived(Iterable<Long> ids);

    List<Appeal> findAllByIdEmployee(Long id, Long first, Long amount);


    void moveToUnderConsideration(Long id);
}