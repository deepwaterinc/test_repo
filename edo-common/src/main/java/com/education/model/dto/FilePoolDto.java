package com.education.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import java.time.ZonedDateTime;
import java.util.UUID;

@ApiModel("Класс FilePoolDto, dto для класса FilePool.class")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilePoolDto {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("ID хранилища файла")
    private UUID storageFileId;
    @ApiModelProperty("Имя хранилища файла")
    private String name;
    @ApiModelProperty("Расширение файла")
    private String extension;
    @ApiModelProperty("Размер файла")
    private Integer size;
    @ApiModelProperty("Количество страниц файла")
    private Integer pageCount;

    @ApiModelProperty("Дата загрузки файла")
    private ZonedDateTime uploadDate;

    @ApiModelProperty("Дата архивирования файла")
    private ZonedDateTime archivedDate;
    @ApiModelProperty("Автор файла")
    private EmployeeDto creator;
}
