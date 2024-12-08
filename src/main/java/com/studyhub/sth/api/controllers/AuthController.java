package com.studyhub.sth.api.controllers;


import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioLoginDto;
import com.studyhub.sth.domain.services.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller")
public class AuthController {
    @Autowired
    IUsuarioService usuarioService;

    @Operation(tags = "Autenticação Controller", summary = "Criar uma conta nova")
    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup(@RequestBody UsuarioCreateDto usuarioNovoDto) throws Exception {
        return new ResponseEntity<>(this.usuarioService.criar(usuarioNovoDto), HttpStatus.CREATED);
    }

    @Operation(tags = "Autenticação Controller", summary = "Entrar em sua conta")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioLoginDto usuarioLoginDto) throws Exception {
        String token = usuarioService.login(usuarioLoginDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}