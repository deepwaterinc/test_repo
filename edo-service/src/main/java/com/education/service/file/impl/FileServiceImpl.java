package com.education.service.file.impl;

import com.education.client.FileRestTemplateClient;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.FilePoolDto;
import com.education.service.file.FileService;
import com.education.service.file_pool.FilePoolService;
import com.education.utils.fileConvertion.FileConversionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

/**
 * Сервис класс для реализации операций над загружаемым файлом.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class FileServiceImpl implements FileService {

    private final FilePoolService filePoolService;
    private final FileConversionService fileConversionService;
    private final FileRestTemplateClient fileRestTemplateClient;

    /**
     * Метод сохраняет полученный файл в файловое хранилище,
     * предварительно сконвертировав его в pdf,
     * создает и возвращает FilePoolDto.
     */
    @Override
    public FilePoolDto saveFile(MultipartFile multipartFile) {

        Map<String, Object> convertedFile = fileConversionService.convertFile(multipartFile);
        var savedFileUUID = fileRestTemplateClient.saveFile((byte[]) convertedFile.get("file"));

        return filePoolService.add(
                FilePoolDto.builder()
                        .storageFileId(savedFileUUID)
                        .name(multipartFile.getOriginalFilename())
                        .extension(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))
                        .size(((byte[]) convertedFile.get("file")).length)
                        .pageCount((int) convertedFile.get("pageCount"))
                        .creator(getCreatorFromSecurity())
                        .build()
        );
    }

    /**
     * Метод возвращает файл из файлового хранилища.
     * @param uuid - название файла в файловом хранилище.
     * @return файл в виде byte[].
     */
    @Override
    public byte[] getFileByUUID(UUID uuid) {
        return fileRestTemplateClient.getFile(uuid);
    }

    /**
     * Метод возвращает EmployeeDto аутентифицированного пользователя.
     */
    private EmployeeDto getCreatorFromSecurity() {
        return EmployeeDto.builder()
                .id(1L)
                .build();
    }
}