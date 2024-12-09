package com.studyhub.sth.api.controllers;


import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.application.dtos.mentor.MentorCreateDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioLoginDto;
import com.studyhub.sth.domain.services.IAlunoService;
import com.studyhub.sth.domain.services.IMentorService;
import com.studyhub.sth.domain.services.IRepresentanteService;
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
    @Autowired
    IAlunoService alunoService;
    @Autowired
    IMentorService mentorService;
    @Autowired
    IRepresentanteService representanteService;

    @Operation(tags = "Auth Controller", summary = "Criar uma conta nova - alunos")
    @PostMapping(value = "/alunos/signup")
    public ResponseEntity<String> signupAlunos(@RequestBody AlunoCreateDto alunoCreateDto) throws Exception {
        return new ResponseEntity<>(this.alunoService.criar(alunoCreateDto), HttpStatus.CREATED);
    }

    @Operation(tags = "Auth Controller", summary = "Criar uma conta nova - mentores")
    @PostMapping(value = "/mentores/signup")
    public ResponseEntity<String> signupMentores(@RequestBody MentorCreateDto mentorCreateDto) throws Exception {
        return new ResponseEntity<>(this.mentorService.criar(mentorCreateDto), HttpStatus.CREATED);
    }

    @Operation(tags = "Auth Controller", summary = "Criar uma conta nova - representantes")
    @PostMapping(value = "/representantes/signup")
    public ResponseEntity<String> signupRepresentantes(@RequestBody RepresentanteCreateDto representanteCreateDto) throws Exception {
        return new ResponseEntity<>(this.representanteService.criarRepresentante(representanteCreateDto), HttpStatus.CREATED);
    }

    @Operation(tags = "Auth Controller", summary = "Entrar em sua conta")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioLoginDto usuarioLoginDto) throws Exception {
        String token = usuarioService.login(usuarioLoginDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}