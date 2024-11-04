package com.studyhub.sth.controllers;

import com.studyhub.sth.repositories.InstituicaoEnsinoRepository;
import com.studyhub.sth.dtos.instituicaoEnsino.*;
import com.studyhub.sth.entities.InstituicaoEnsino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.studyhub.sth.services.instituicaoEnsino.InstituicaoEnsinoService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/instituicoes")
public class InstituicaoEnsinoController {
        @Autowired
        private InstituicaoEnsinoService instituicaoEnsinoService;
        
        @Autowired
        private InstituicaoEnsinoRepository instituicaoEnsinoRepository;
    
        @GetMapping
        public List<InstituicaoEnsinoDto> getAll() {
            return instituicaoEnsinoService.findAll();
        }
    
        @GetMapping("/{id}")
        public ResponseEntity<InstituicaoEnsinoDto> getById(@PathVariable UUID id) {
            var instituicao = instituicaoEnsinoService.findById(id);
            return instituicao != null ? ResponseEntity.ok(instituicao) : ResponseEntity.notFound().build();
        }
    
        @PostMapping
        public InstituicaoEnsinoDto create(@RequestBody InstituicaoEnsinoCreateDto instituicao) {
            return instituicaoEnsinoService.save(instituicao);
        }
    
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable UUID id) {
            instituicaoEnsinoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        @PutMapping("/{id}")
        public ResponseEntity<InstituicaoEnsinoDto> update(@PathVariable("id") UUID id, @RequestBody InstituicaoEnsinoUpdateDto instituicaoEnsinoDto) {
            var updatedInstituicao = instituicaoEnsinoService.update(id, instituicaoEnsinoDto);
            return ResponseEntity.ok(updatedInstituicao);
    }

        @GetMapping("/{nome}")
        public List<InstituicaoEnsino> buscarPorNome(@PathVariable String nome) {
            return instituicaoEnsinoRepository.findByNomeContaining(nome);
    }
    }
    


