package com.education.author_feign.controller;

import com.education.author_feign.service.AuthorService;
import com.education.model.dto.AuthorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/service/author")
@AllArgsConstructor
@Log4j2
@Tag(name = "Контроллер Автора", description = "Контроллер Автора для выполнения веб-запросов (модуль edo-service)")
public class AuthorServiceController {
    private final AuthorService authorService;

    @Operation(summary = "Поиск сущности по id")
    @GetMapping("/{id}")
    public AuthorDto showById(@PathVariable("id") Long id) {
        log.info("отправил AuthorDto.class");
        return authorService.findById(id);
    }

    @Operation(summary = "Поиск сущностей по значениям их id")
    @PostMapping("/findAll")
    public ResponseEntity<List<AuthorDto>> showAllById(@RequestBody List<String> ids) {
        log.info("отправил list AuthorDto.class");
        return ResponseEntity.ok(authorService.findAllById(ids));
    }

    @Operation(summary = "Сохранение сущности")
    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto author) {
        author = authorService.save(author).getBody();
        log.info("сохранил AuthorDto.class");
        return author != null ?
                ResponseEntity.ok(author)
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
