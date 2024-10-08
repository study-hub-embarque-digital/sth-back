package com.studyhub.sth.controllers;

import com.studyhub.sth.dtos.users.NovoUsuarioDto;
import com.studyhub.sth.dtos.users.UsuarioAtualizadoDto;
import com.studyhub.sth.dtos.users.UsuarioDto;
import com.studyhub.sth.entities.Usuario;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.services.usuarios.IUsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários Controller")
public class UsuariosController {
    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<UsuarioDto> criar(@RequestBody NovoUsuarioDto novoUsuarioDto) {
        return new ResponseEntity<>(this.usuarioService.criar(novoUsuarioDto), HttpStatus.CREATED);
    }

    @PutMapping("{usuarioId}")
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable UUID usuarioId, @RequestBody UsuarioAtualizadoDto usuarioAtualizadoDto) throws ElementoNaoEncontradoExcecao {
        return new ResponseEntity<>(this.usuarioService.atualizar(usuarioId, usuarioAtualizadoDto), HttpStatus.OK);
    }
}
