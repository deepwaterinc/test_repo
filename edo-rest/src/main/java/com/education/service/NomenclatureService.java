package com.education.service;

import com.education.model.dto.NomenclatureDto;

import java.util.List;

public interface NomenclatureService {

    List<NomenclatureDto> findByIndex(String index);
}
