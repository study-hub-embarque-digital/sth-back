package com.studyhub.sth.application.dtos.alunos;

import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import com.studyhub.sth.domain.enums.Ciclo;
import com.studyhub.sth.domain.enums.Cursos;
import com.studyhub.sth.domain.enums.Periodo;
import jakarta.validation.Valid;
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
    private Cursos curso;
    private UUID instituicaoEnsinoId;
    private Ciclo ciclo;
    private Boolean isWorkingInIt;
    private Boolean isExemptedResidence;
    @Valid
    private String entrada;
}