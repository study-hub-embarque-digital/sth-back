package com.studyhub.sth.application.dtos.encontro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EncontroChamadaEResumoDto {
    private List<UUID> alunosPresentesId;
    private String resumo;
}
