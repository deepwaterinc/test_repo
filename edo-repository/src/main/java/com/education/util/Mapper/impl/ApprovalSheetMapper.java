package com.education.util.Mapper.impl;

import com.education.entity.ApprovalSheet;
import com.education.model.dto.ApprovalSheetDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

/**
 * @author Ivan Chursinov
 */

@Mapper(componentModel = "spring")
public interface ApprovalSheetMapper extends Mappable<ApprovalSheet, ApprovalSheetDto> {
}
