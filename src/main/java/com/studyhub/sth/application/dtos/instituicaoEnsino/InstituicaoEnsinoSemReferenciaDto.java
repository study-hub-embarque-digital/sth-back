package com.studyhub.sth.application.dtos.instituicaoEnsino;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoEnsinoSemReferenciaDto {
    private UUID instituicaoEnsinoId;
    private String nome;
    private String endereco;
    private String coordenador;
}
