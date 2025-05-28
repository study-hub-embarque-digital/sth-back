package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.representante.RepresentanteCreateDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteUpdateDto;
import com.studyhub.sth.domain.entities.Representante;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IRepresentanteService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/representantes")
@Tag(name = "Representantes Controller")
public class RepresentanteController {
    @Autowired
    private IRepresentanteService representanteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RepresentanteDto> criarRepresentante(@RequestBody RepresentanteCreateDto dto) throws Exception {
        return new ResponseEntity<>(representanteService.criarRepresentante(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RepresentanteDto>> listarRepresentantes() {
        return ResponseEntity.ok(representanteService.listarRepresentantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepresentanteDto> obterRepresentantePorId(@PathVariable UUID id) {
        return ResponseEntity.ok(representanteService.obterRepresentantePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepresentanteDto> atualizarRepresentante(@PathVariable UUID id, @RequestBody RepresentanteUpdateDto dto) {
        return ResponseEntity.ok(representanteService.atualizarRepresentante(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarRepresentante(@PathVariable UUID id) {
        representanteService.deletarRepresentante(id);
    }
}
