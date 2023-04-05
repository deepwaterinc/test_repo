package com.education.service.approvalsheet;

import com.education.model.dto.ApprovalSheetDto;

/**
 * @author Ivan Chursinov
 */

public interface ApprovalSheetService {

    ApprovalSheetDto save(ApprovalSheetDto approvalSheet);
    void moveToArchive(Long id);
    ApprovalSheetDto findById(Long id);
    ApprovalSheetDto findByIdNotArchived(Long id);

}
