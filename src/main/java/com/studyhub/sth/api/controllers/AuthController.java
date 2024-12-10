package com.studyhub.sth.api.controllers;


import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.application.dtos.auth.AuthResponse;
import com.studyhub.sth.application.dtos.auth.RefreshRequest;
import com.studyhub.sth.application.dtos.mentor.MentorCreateDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioLoginDto;
import com.studyhub.sth.domain.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @Operation(tags = "Auth Controller", summary = "Criar uma conta nova - alunos")
    @PostMapping(value = "/alunos/signup")
    public ResponseEntity<AuthResponse> signupAlunos(@RequestBody AlunoCreateDto alunoCreateDto) throws Exception {
        return new ResponseEntity<>(this.authService.criaAluno(alunoCreateDto), HttpStatus.CREATED);
    }

    @Operation(tags = "Auth Controller", summary = "Criar uma conta nova - mentores")
    @PostMapping(value = "/mentores/signup")
    public ResponseEntity<AuthResponse> signupMentores(@RequestBody MentorCreateDto mentorCreateDto) throws Exception {
        return new ResponseEntity<>(this.authService.criaMentor(mentorCreateDto), HttpStatus.CREATED);
    }

    @Operation(tags = "Auth Controller", summary = "Criar uma conta nova - representantes")
    @PostMapping(value = "/representantes/signup")
    public ResponseEntity<AuthResponse> signupRepresentantes(@RequestBody RepresentanteCreateDto representanteCreateDto) throws Exception {
        return new ResponseEntity<>(this.authService.criaRepresentante(representanteCreateDto), HttpStatus.CREATED);
    }

    @Operation(tags = "Auth Controller", summary = "Entrar em sua conta")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UsuarioLoginDto usuarioLoginDto) throws Exception {
        return new ResponseEntity<>(this.authService.login(usuarioLoginDto), HttpStatus.OK);
    }

    @Operation(tags = "Auth Controller", summary = "Atualizar sessão")
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) throws Exception {
        return new ResponseEntity<>(this.authService.refresh(refreshRequest), HttpStatus.OK);
    }
}