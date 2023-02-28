package com.education.service.appeal.impl;

import com.education.entity.Appeal;
import com.education.repository.AppealRepository;
import com.education.service.appeal.AppealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppealServiceImpl implements AppealService {

    final AppealRepository appealRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Appeal save(Appeal appeal) {
        appeal.setCreationDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        return appealRepository.save(appeal);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToArchive(Long id) {
        appealRepository.moveToArchive(id);
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Appeal findById(Long id) {
        return appealRepository.findById(id).get();
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Appeal> findAllById(Iterable<Long> ids) {
        return appealRepository.findAllById(ids);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Appeal findByIdNotArchived(Long id) {
        return appealRepository.findByIdNotArchived(id).orElse(null);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Appeal> findAllByIdNotArchived(Iterable<Long> ids) {
        return appealRepository.findAllByIdNotArchived(ids);

    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Appeal> findAllByIdEmployee(Long id, Long first, Long amount) {

        return appealRepository.findByIdEmployee(id)
                .stream()
                .sorted(Comparator.comparingLong(Appeal::getId))
                .skip(first - 1)
                .limit(amount)
                .collect(Collectors.toList());
    }
}
