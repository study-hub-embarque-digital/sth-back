package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.discussao.DiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.NewDiscussaoDto;
import com.studyhub.sth.application.dtos.duvida.*;
import com.studyhub.sth.domain.entities.Duvida;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IDuvidaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api/duvidas")
@Tag(name = "Duvidas Controller")
public class DuvidaController {
    @Autowired
    private IDuvidaService duvidaService;

    @GetMapping
    public ResponseEntity<List<DuvidaDto>> getAll() {
        return ResponseEntity.ok(this.duvidaService.findAll());
    }

    @GetMapping("solucao/{id}")
    public ResponseEntity<DuvidaSolucoesDto> findByIdSolucao(@PathVariable UUID id) {
    DuvidaSolucoesDto duvidaDto = duvidaService.findByIdWithSolucao(id);
    return ResponseEntity.ok(duvidaDto);
    }

    @PostMapping
    public ResponseEntity<DuvidaDto> create(@RequestBody NewDuvidaDto newDuvidaDto) {
        return new ResponseEntity<>(this.duvidaService.create(newDuvidaDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DuvidaDto> update(@PathVariable UUID id, @RequestBody UpdateDuvidaDto updateDuvidaDto) {
        DuvidaDto dto = duvidaService.update(id, updateDuvidaDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        duvidaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
