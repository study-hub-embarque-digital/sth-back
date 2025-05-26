package com.studyhub.sth.application.dtos.empregador;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpregadorResumoDto {
    private UUID empregadorId;
    private String nomeEmpresa;
}
