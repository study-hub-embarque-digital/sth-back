package com.studyhub.sth.application.dtos.instituicaoEnsino;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituicaoEnsinoCreateDto {
    private String nome;
    private String endereco;
    private String coordenador;
}

