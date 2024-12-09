package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IAlunoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/alunos")
@Tag(name = "Alunos Controller")
public class AlunosController {
    @Autowired
    private IAlunoService alunoService;

    @PostMapping()
    public ResponseEntity<String> criar(@RequestBody AlunoCreateDto novoAlunoDto) throws Exception {
        return new ResponseEntity<>(this.alunoService.criar(novoAlunoDto), HttpStatus.CREATED);
    }

    @PutMapping("{alunoId}")
    public ResponseEntity<AlunoDto> atualizar(@PathVariable UUID alunoId, @RequestBody AlunoUpdateDto alunoAtualizadoDto) throws ElementoNaoEncontradoExcecao {
        return new ResponseEntity<>(this.alunoService.atualizar(alunoId, alunoAtualizadoDto), HttpStatus.OK);
    }

    @GetMapping("{usuarioId}")
    public ResponseEntity<AlunoDto> detalhar(@PathVariable UUID usuarioId) throws ElementoNaoEncontradoExcecao {
        return new ResponseEntity<>(this.alunoService.detalhar(usuarioId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<AlunoDto>> listarTodos() throws ElementoNaoEncontradoExcecao {
        return new ResponseEntity<>(this.alunoService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/por-periodo")
    public ResponseEntity<List<AlunoDto>> obterAlunosPorPeriodo(@RequestParam int periodo) {
        return new ResponseEntity<>(this.alunoService.listarPorPeriodo(periodo), HttpStatus.OK);
    }
}
