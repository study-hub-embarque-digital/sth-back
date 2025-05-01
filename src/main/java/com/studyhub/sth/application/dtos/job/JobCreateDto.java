package com.studyhub.sth.application.dtos.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobCreateDto {
    private String cargo;
    private String areaAtuacao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private UUID usuarioId;
    private UUID empregadorId;
}
