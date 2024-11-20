package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.discussao.DiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.NewDiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.UpdatedDiscussaoDto;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IDiscussaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/discussoes")
@Tag(name = "Discussões Controller")
public class DiscussaoController {
    @Autowired
    private IDiscussaoService discussaoService;

    @GetMapping
    public ResponseEntity<List<DiscussaoDto>> getAll() {
        return ResponseEntity.ok(this.discussaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DiscussaoDto>> getAllChild(@PathVariable UUID discussaoId) throws ElementoNaoEncontradoExcecao {
        return ResponseEntity.ok(this.discussaoService.findAllChild(discussaoId));
    }

    @PostMapping
    public ResponseEntity<DiscussaoDto> create(@RequestBody NewDiscussaoDto newDiscussaoDto) {
        return new ResponseEntity<>(this.discussaoService.create(newDiscussaoDto), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<DiscussaoDto> createChild(@RequestBody NewDiscussaoDto newDiscussaoDto, @PathVariable UUID discussaoPaiId) throws ElementoNaoEncontradoExcecao {
        return new ResponseEntity<>(this.discussaoService.createChild(newDiscussaoDto, discussaoPaiId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscussaoDto> update(@RequestBody UpdatedDiscussaoDto updatedDiscussaoDto, @PathVariable UUID discussaoId) throws Exception {
        return ResponseEntity.ok(this.discussaoService.update(discussaoId, updatedDiscussaoDto));
    }
}
