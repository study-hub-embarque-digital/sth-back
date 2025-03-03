package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.admins.AdminCreateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.application.dtos.auth.AuthResponse;
import com.studyhub.sth.application.dtos.auth.RefreshRequest;
import com.studyhub.sth.application.dtos.mentor.MentorCreateDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioLoginDto;

public interface IAuthService {
    AuthResponse criaAluno(AlunoCreateDto novoAlunoDto) throws Exception;
    AuthResponse criaMentor(MentorCreateDto dto) throws Exception;
    AuthResponse criaRepresentante(RepresentanteCreateDto dto) throws Exception;
    AuthResponse criaAdmin(AdminCreateDto dto) throws Exception;
    AuthResponse login(UsuarioLoginDto usuarioLoginDto) throws Exception;
    AuthResponse refresh(RefreshRequest refreshRequest) throws Exception;
}
