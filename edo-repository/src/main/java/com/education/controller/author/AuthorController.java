package com.education.controller.author;

import com.education.entity.Author;
import com.education.model.dto.AuthorDto;
import com.education.service.author.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/repository/author")
@Log4j2
@Tag(name = "Контроллер Автора", description = "Контроллер Автора для выполнения веб-запросов (модуль edo-repository)")
public class AuthorController {

    private final AuthorService authorService;

    @Operation(summary = "Поиск сущности по id")
    @GetMapping("/{id}")
    public AuthorDto showById(@PathVariable("id") Long id) {
        log.info("отправил AuthorDto.class");
        return Author.authorToDto(authorService.findById(id));
    }

    @Operation(summary = "Поиск сущностей по значениям их id")
    @PostMapping("/findAll")
    public List<AuthorDto> showAllById(@RequestBody List<Long> ids) {
        log.info("отправил list AuthorDto.class");
        return authorService.findAllById(ids);
    }

    @Operation(summary = "Сохранение сущности")
    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto authorDto) {
        authorDto = authorService.save(authorDto);
        log.info("сохранил AuthorDto.class");
        return authorDto != null ?
                ResponseEntity.ok(authorDto)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Удаление сущности")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        log.info("удалил AuthorDto.class");
        return ResponseEntity.ok("DELETED");
    }

}
