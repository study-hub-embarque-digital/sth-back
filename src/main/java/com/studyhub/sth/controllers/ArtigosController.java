package com.studyhub.sth.controllers;

import com.studyhub.sth.dtos.artigos.ArtigoAtualizadoDto;
import com.studyhub.sth.dtos.artigos.ArtigoDto;
import com.studyhub.sth.dtos.artigos.NovoArtigoDto;
import com.studyhub.sth.services.artigos.IArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/artigos")
public class ArtigosController {

    @Autowired
    private IArtigoService artigoService;

    @PostMapping
    public ResponseEntity<ArtigoDto> criar(@RequestBody NovoArtigoDto novoArtigoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artigoService.criar(novoArtigoDto));
    }

    @PutMapping("/{artigoId}")
    public ResponseEntity<ArtigoDto> atualizar(@PathVariable UUID artigoId, @RequestBody ArtigoAtualizadoDto artigoAtualizadoDto) {
        return ResponseEntity.ok(artigoService.atualizar(artigoId, artigoAtualizadoDto));
    }

    @GetMapping("/{artigoId}")
    public ResponseEntity<ArtigoDto> detalhar(@PathVariable UUID artigoId) {
        return ResponseEntity.ok(artigoService.detalhar(artigoId));
    }

    @GetMapping
    public ResponseEntity<List<ArtigoDto>> listar() {
        return ResponseEntity.ok(artigoService.listarTodos());
    }

    @DeleteMapping("/{artigoId}")
    public ResponseEntity<Void> deletar(@PathVariable UUID artigoId) {
        artigoService.deletar(artigoId);
        return ResponseEntity.noContent().build();  
    }
}
