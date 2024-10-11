package com.studyhub.sth.controllers;

import com.studyhub.sth.repositories.InstituicaoEnsinoRepository;
import com.studyhub.sth.dtos.InstituicaoEnsino.*;
import com.studyhub.sth.entities.InstituicaoEnsino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.studyhub.sth.services.InstituicaoEnsino.InstituicaoEnsinoService;
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
        public List<InstituicaoEnsino> getAll() {
            return instituicaoEnsinoService.findAll();
        }
    
        @GetMapping("/{id}")
        public ResponseEntity<InstituicaoEnsino> getById(@PathVariable UUID id) {
            InstituicaoEnsino instituicao = instituicaoEnsinoService.findById(id);
            return instituicao != null ? ResponseEntity.ok(instituicao) : ResponseEntity.notFound().build();
        }
    
        @PostMapping
        public InstituicaoEnsino create(@RequestBody InstituicaoEnsinoDto instituicao) {
            return instituicaoEnsinoService.save(instituicao);
        }
    
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable UUID id) {
            instituicaoEnsinoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        @PutMapping("/{id}")
        public ResponseEntity<InstituicaoEnsino> update(@PathVariable("id") UUID id, @RequestBody UpdateInstituicaoEnsinoDto instituicaoEnsinoDto) {
            InstituicaoEnsino updatedInstituicao = instituicaoEnsinoService.update(id, instituicaoEnsinoDto);
            return ResponseEntity.ok(updatedInstituicao);
    }

        @GetMapping("/{nome}")
        public List<InstituicaoEnsino> buscarPorNome(@PathVariable String nome) {
            return instituicaoEnsinoRepository.findByNomeContaining(nome);
    }
    }
    


