package com.education.service.nomenclature;

import com.education.model.dto.NomenclatureDto;

import java.util.List;

/**
 * Представляет список операций над номенклатурой
 *
 * @author Иван Кузнецов
 * @version 1.0
 * @since 1.0
 */

public interface NomenclatureService {

    /**
     * Сохраняет номенклатуру
     *
     * @param nomenclature NomenclatureDto
     * @return NomenclatureDto
     */
    NomenclatureDto save(NomenclatureDto nomenclature);

    /**
     * Переводит номенклатуру в архив
     *
     * @param id Long
     */
    void moveToArchive(Long id);

    /**
     * Предоставляет NomenclatureDto номенклатуры по id
     *
     * @param id Long
     * @return NomenclatureDto
     */
    NomenclatureDto findById(Long id);

    /**
     * Предоставляет список NomenclatureDto номенклатур по id
     *
     * @param list List of id
     * @return List of NomenclatureDto
     */
    List<NomenclatureDto> findAllById(List<Long> list);

    /**
     * Предоставляет не заархивированное NomenclatureDto номенклатуры по id
     *
     * @param id Long
     * @return NomenclatureDto
     */
    NomenclatureDto findByIdNotArchived(Long id);

    /**
     * Предоставляет список не заархивированных NomenclatureDto номенклатур из БД по id
     *
     * @param list List of id
     * @return List of NomenclatureDto
     */
    List<NomenclatureDto> findAllByIdNotArchived(List<Long> list);

    /**
     * Предоставляет список номенклатур из БД по индексу
     *
     * @return List of NomenclatureDto
     */
    List<NomenclatureDto> findByIndex(String index);

    /**
     * Преобразует номер обращения из шаблона номенклатуры
     *
     * @param nomenclatureDto NomenclatureDto
     * @return String of NomenclatureDto
     */
    String getNumberFromTemplate(NomenclatureDto nomenclatureDto);
}
