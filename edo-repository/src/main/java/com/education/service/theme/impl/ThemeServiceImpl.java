package com.education.service.theme.impl;

import com.education.entity.Theme;
import com.education.model.dto.ThemeDto;
import com.education.repository.theme.ThemeRepository;
import com.education.service.theme.ThemeService;
import com.education.util.Mapper.impl.ThemeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для темы обращения
 */
@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;
    private final ThemeMapper mapper;

    /**
     * Получает ThemeDto и сохраняет тему
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ThemeDto save(ThemeDto themeDto) {
        Theme theme = mapper.toEntity(themeDto);

        return mapper.toDto(themeRepository.save(theme));
    }

    /**
     * Помещает тему с переданным id в архивные, проставляя ей archivedDate
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer moveToArchive(Long id) {
        return themeRepository.moveToArchive(id);
    }

    /**
     * Выдает тему по id
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ThemeDto findById(Long id) {
        Theme theme = themeRepository.findById(id).orElse(null);
        if (theme == null) {
            return null;
        } else {
            return mapper.toDto(theme);
        }
    }

    /**
     * Выдает темы по списку id
     */
    @Cacheable(value = "cacheForAllTheme")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ThemeDto> findAllById(List<Long> ids) {

        return themeRepository.findAllById(ids).stream().map(mapper::toDto).toList();
    }


    /**
     * Выдает тему по id, если она не в архивных (т.е. архивная дата отсутствует)
     */


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ThemeDto findByIdNotArchived(Long id) {

        return themeRepository.findByIdAndArchivedDateIsNull(id) == null
                ? null
                : mapper.toDto(themeRepository.findByIdAndArchivedDateIsNull(id));
    }

    /**
     * Выдает по списку id соответствующие темы, которые не являются архивными
     */
    @Cacheable(value = "сacheForAllThemeNotArchive")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ThemeDto> findAllByIdNotArchived(List<Long> ids) {

        return themeRepository.findAllNotArchived(ids).stream().map(mapper::toDto).toList();
    }
}