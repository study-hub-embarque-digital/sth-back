package com.studyhub.sth.controllers;

import com.studyhub.sth.dtos.representante.RepresentanteCreateDto;
import com.studyhub.sth.entities.Representante;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.services.representantes.IRepresentanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/representantes")
public class RepresentanteController {
    @Autowired
    private IRepresentanteService representanteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Representante criarRepresentante(@RequestBody RepresentanteCreateDto dto) throws ElementoNaoEncontradoExcecao {
        return representanteService.criarRepresentante(dto);
    }

    @GetMapping
    public List<Representante> listarRepresentantes() {
        return representanteService.listarRepresentantes();
    }

    @GetMapping("/{id}")
    public Representante obterRepresentantePorId(@PathVariable UUID id) {
        return representanteService.obterRepresentantePorId(id);
    }

    @PutMapping("/{id}")
    public Representante atualizarRepresentante(@PathVariable UUID id, @RequestBody RepresentanteCreateDto dto) {
        return representanteService.atualizarRepresentante(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarRepresentante(@PathVariable UUID id) {
        representanteService.deletarRepresentante(id);
    }
}
