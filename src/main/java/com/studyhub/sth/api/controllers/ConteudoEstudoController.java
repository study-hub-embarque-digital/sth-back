package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoDto;
import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoUpdateDto;
import com.studyhub.sth.application.services.ConteudoEstudoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoCreateDto;

@RestController
@RequestMapping("/api/conteudos-estudo")
@Tag(name = "Conteudos de Estudo Controller")
public class ConteudoEstudoController {
    @Autowired
    private ConteudoEstudoService conteudoEstudoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ConteudoEstudoCreateDto> criarConteudoEstudo(@RequestBody ConteudoEstudoCreateDto dto) {
        return ResponseEntity.ok(conteudoEstudoService.criarConteudoEstudo(dto));
    }

    @GetMapping
    public ResponseEntity<List<ConteudoEstudoCreateDto>> listarConteudosEstudo() {
        return ResponseEntity.ok(conteudoEstudoService.listarConteudosEstudo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConteudoEstudoDto> obterConteudoEstudoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(conteudoEstudoService.obterConteudoEstudoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConteudoEstudoDto> atualizarConteudoEstudo(@PathVariable UUID id, @RequestBody ConteudoEstudoUpdateDto dto) {
        return ResponseEntity.ok(conteudoEstudoService.atualizarConteudoEstudo(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarConteudoEstudo(@PathVariable UUID id) {
        conteudoEstudoService.deletarConteudoEstudo(id);
    }
}
