package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.domain.enums.Periodo;
import com.studyhub.sth.domain.enums.Turno;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IAlunoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/alunos")
@Tag(name = "Alunos Controller")
public class AlunosController {
    @Autowired
    private IAlunoService alunoService;

    @PostMapping()
    public ResponseEntity<AlunoDto> criar(@RequestBody AlunoCreateDto novoAlunoDto) throws Exception {
        return new ResponseEntity<>(this.alunoService.criar(novoAlunoDto), HttpStatus.CREATED);
    }

    @PutMapping("{alunoId}")
    public ResponseEntity<AlunoDto> atualizar(@PathVariable UUID alunoId, @RequestBody AlunoUpdateDto alunoAtualizadoDto) throws ElementoNaoEncontradoExcecao, IOException {
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
    public ResponseEntity<List<AlunoDto>> obterAlunosPorPeriodo(@RequestParam Periodo periodo) {
        return new ResponseEntity<>(this.alunoService.listarPorPeriodo(periodo), HttpStatus.OK);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<AlunoDto>> buscarAlunosDisponiveis(
            @RequestParam UUID instituicaoId,
            @RequestParam @Valid  Periodo periodo,
            @RequestParam Turno turno
    ) {
        return new ResponseEntity<>(alunoService.buscarDisponiveisPorFiltro(instituicaoId, periodo, turno), HttpStatus.OK);
    }

}
