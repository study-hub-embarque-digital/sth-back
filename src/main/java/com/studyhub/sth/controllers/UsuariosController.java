package com.studyhub.sth.controllers;

import com.studyhub.sth.dtos.users.NovoUsuarioDto;
import com.studyhub.sth.entities.Usuario;
import com.studyhub.sth.services.usuarios.IUsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários Controller")
public class UsuariosController {
    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<Usuario> criar(@RequestBody NovoUsuarioDto novoUsuarioDto) {
        return new ResponseEntity<>(this.usuarioService.criar(novoUsuarioDto), HttpStatus.CREATED);
    }
}
