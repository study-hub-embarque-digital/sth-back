package com.studyhub.sth.controllers;

import com.studyhub.sth.dtos.alunos.NovoAlunoDto;
import com.studyhub.sth.dtos.users.NovoUsuarioDto;
import com.studyhub.sth.dtos.users.UsuarioAtualizadoDto;
import com.studyhub.sth.dtos.users.UsuarioDto;
import com.studyhub.sth.entities.Aluno;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.services.alunos.IAlunoService;
import com.studyhub.sth.services.usuarios.IUsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios/{usuarioId}/alunos")
@Tag(name = "Alunos Controller")
public class AlunosController {
    @Autowired
    private IAlunoService alunoService;

    @PostMapping()
    public ResponseEntity<Aluno> criar(@PathVariable UUID usuarioId, @RequestBody NovoAlunoDto novoAlunoDto) throws ElementoNaoEncontradoExcecao {
        return new ResponseEntity<>(this.alunoService.criar(usuarioId, novoAlunoDto), HttpStatus.CREATED);
    }

//    @PutMapping("{usuarioId}")
//    public ResponseEntity<UsuarioDto> atualizar(@PathVariable UUID usuarioId, @RequestBody UsuarioAtualizadoDto usuarioAtualizadoDto) throws ElementoNaoEncontradoExcecao {
//        return new ResponseEntity<>(this.alunoService.atualizar(usuarioId, usuarioAtualizadoDto), HttpStatus.OK);
//    }
//
//    @GetMapping("{usuarioId}")
//    public ResponseEntity<UsuarioDto> detalhar(@PathVariable UUID usuarioId) throws ElementoNaoEncontradoExcecao {
//        return new ResponseEntity<>(this.alunoService.detalhar(usuarioId), HttpStatus.OK);
//    }
}
