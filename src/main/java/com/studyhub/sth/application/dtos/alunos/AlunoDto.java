package com.studyhub.sth.application.dtos.alunos;

import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoSemReferenciaDto;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.domain.before.enums.Ciclo;
import com.studyhub.sth.domain.before.enums.Periodo;
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
    private Periodo periodo;
    private String curso;
    private UsuarioDto usuarioDto;
    private InstituicaoEnsinoSemReferenciaDto instituicaoEnsinoDto;
    private Ciclo ciclo;
    private boolean isWorkingInIt;
    private boolean isExemptedResidence;
}