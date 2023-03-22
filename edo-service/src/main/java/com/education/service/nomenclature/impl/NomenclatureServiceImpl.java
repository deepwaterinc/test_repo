package com.education.service.nomenclature.impl;

import com.education.client.NomenclatureRestTemplateClient;
import com.education.model.dto.NomenclatureDto;
import com.education.service.nomenclature.NomenclatureService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Представляет реализацию операций над номенклатурой
 *
 * @author Иван Кузнецов
 * @version 1.0
 * @since 1.0
 */

@Service
@AllArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {

    /**
     * Клиент для связи с модулем edo-repository
     */
    private final NomenclatureRestTemplateClient client;

    /**
     * Сохраняет номенклатуру
     *
     * @param nomenclature NomenclatureDto
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto save(NomenclatureDto nomenclature) {
        return client.save(nomenclature);
    }

    /**
     * Переводит номенклатуру в архив
     *
     * @param id Long
     */
    @Override
    public void moveToArchive(Long id) {
        client.moveToArchive(id);
    }

    /**
     * Предоставляет NomenclatureDto номенклатуры по id
     *
     * @param id Long
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto findById(Long id) {
        return client.findById(id);
    }

    /**
     * Предоставляет список NomenclatureDto номенклатур по id
     *
     * @param list List of id
     * @return List of NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findAllById(List<Long> list) {
        return client.findAllById(list);
    }

    /**
     * Предоставляет не заархивированное NomenclatureDto номенклатуры по id
     *
     * @param id Long
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto findByIdNotArchived(Long id) {
        return client.findByIdNotArchived(id);
    }

    /**
     * Предоставляет список не заархивированных NomenclatureDto номенклатур из БД по id
     *
     * @param list List of id
     * @return List of NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findAllByIdNotArchived(List<Long> list) {
        return client.findAllByIdNotArchived(list);
    }

    /**
     * Предоставляет список номенклатур из БД по индексу
     *
     * @return List of NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findByIndex(String index) {
        if (index.length() < 2) {
            return null;
        } else {
            return client.findByIndex(index);
        }
    }

    /**
     * Преобразует номер обращения из шаблона номенклатуры
     *
     * @param nomenclatureDto NomenclatureDto
     * @return String of NomenclatureDto
     */
    @Override
    public String getNumberFromTemplate(NomenclatureDto nomenclatureDto) {
        final String TEMPLATE = "%ЧИС%ГОД-%ЗНАЧ/2";
        var temp = nomenclatureDto.getTemplate();
        if (temp == null) {
            temp = TEMPLATE;
        }
        String currentValue = nomenclatureDto.getCurrentValue().toString();
        nomenclatureDto.setCurrentValue(Long.parseLong(currentValue) + 1);
        client.save(nomenclatureDto);
        String year = String.format("%02d", Calendar.getInstance().get(Calendar.YEAR) % 100);
        String day = String.format("%02d", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        return temp
//  убирает больше двух знаков "%" подряд, оставляя один
                .replaceAll("%{2,}", "%")
//  заменяет число дня
                .replaceAll("%чис|%ЧИС|%число|%ЧИСЛО", day)
//  заменяет год
                .replaceAll("%год|%ГОД", year)
//  заменяет значение
                .replaceAll("%знач|%ЗНАЧ|%значение|%ЗНАЧЕНИЕ", StringUtils.isEmpty(currentValue) ? "" : currentValue)
//  убирает проценты с флагом
                .replaceAll("%[\\W][^(а-яА-Я)]", "")
//  убирает больше двух знаков "-" подряд, оставляя один
                .replaceAll("-{2,}", "-")
//  убирает больше двух знаков "/" подряд, оставляя один
                .replaceAll("/{2,}", "/")
//  убирает знаки "-" и "/" в начале и в конце
                .replaceAll("(^[-/]+)|([-/]*$)", "");
    }
}
