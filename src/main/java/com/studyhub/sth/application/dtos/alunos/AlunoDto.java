package com.studyhub.sth.application.dtos.alunos;

import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoSemReferenciaDto;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoDto {
    private UUID alunoId;
    private int periodo;
    private String curso;
    private UsuarioDto usuarioDto;
    private InstituicaoEnsinoSemReferenciaDto instituicaoEnsinoDto;
}