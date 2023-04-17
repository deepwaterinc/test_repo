package com.education.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDto{
    @SuppressWarnings("java:S1948")
    @ApiModelProperty("Файл")
    private MultipartFile file;

    @ApiModelProperty("url файла")
    private String url;

    @ApiModelProperty("размер файла")
    private Long size;

    @ApiModelProperty("имя файла, до MinIO")
    private String filename;

    @ApiModelProperty("uuid имя файла")
    private String uuidName;

}
