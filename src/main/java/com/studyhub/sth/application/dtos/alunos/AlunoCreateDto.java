package com.studyhub.sth.application.dtos.alunos;

import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import com.studyhub.sth.domain.before.enums.Ciclo;
import com.studyhub.sth.domain.before.enums.Periodo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoCreateDto {
    private UsuarioCreateDto novoUsuarioDto;
    private Periodo periodo;
    private String curso;
    private UUID instituicaoEnsinoId;
    private Ciclo ciclo;
    private boolean isWorkingInIt;
    private boolean isExemptedResidence;
}