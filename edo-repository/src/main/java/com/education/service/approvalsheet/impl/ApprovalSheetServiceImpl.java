package com.education.service.approvalsheet.impl;

import com.education.entity.ApprovalSheet;
import com.education.model.dto.ApprovalSheetDto;
import com.education.repository.ApprovalSheetRepository;
import com.education.service.approvalsheet.ApprovalSheetService;
import com.education.util.Mapper.impl.ApprovalSheetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Chursinov
 */

@Service
@RequiredArgsConstructor
public class ApprovalSheetServiceImpl implements ApprovalSheetService {

    private final ApprovalSheetRepository approvalSheetRepository;
    private final ApprovalSheetMapper mapper;

    /**
     * Метод для сохранения листа согласования.
     * @param approvalSheetDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApprovalSheetDto save(ApprovalSheetDto approvalSheetDto) {
        return mapper.toDto(approvalSheetRepository.save(mapper.toEntity(approvalSheetDto)));
    }

    /**
     * Метод для архивирования листа согласования.
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {
        approvalSheetRepository.moveToArchive(id);
    }

    /**
     * Метод для поиска листа согласования по id.
     * @param id
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ApprovalSheetDto findById(Long id) {
        Optional<ApprovalSheet> approvalSheet = approvalSheetRepository.findById(id);
        return approvalSheet
                .map(mapper::toDto)
                .orElse(null);
    }

    /**
     * Предоставляет список ApprovalSheetDto листов согласования из БД по id
     * @param ids List of id
     * @return List of ApprovalSheetDto
     */

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<ApprovalSheetDto> findAllById(List<Long> ids) {
        List<ApprovalSheet> approvalSheets = approvalSheetRepository.findAllById(ids);
        return approvalSheets.isEmpty() ? null : mapper.toDto(approvalSheets);
    }

    /**
     * Метод для поиска листа согласования по id, не находящегося в архиве.
     * @param id
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ApprovalSheetDto findByIdNotArchived(Long id) {
        Optional<ApprovalSheet> approvalSheet = approvalSheetRepository.findByIdNotArchived(id);
        return approvalSheet
                .map(mapper::toDto)
                .orElse(null);
    }

    /**
     * Предоставляет список не заархивированных ApprovalSheetDto листов согласования из БД по id
     * @param ids List of id
     * @return List of ApprovalSheetDto
     */

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ApprovalSheetDto> findAllByIdNotArchived(List<Long> ids) {
        List<ApprovalSheet> approvalSheets = approvalSheetRepository.findAllByIdNotArchived(ids);
        return approvalSheets.isEmpty() ? null : mapper.toDto(approvalSheets);
    }
}
