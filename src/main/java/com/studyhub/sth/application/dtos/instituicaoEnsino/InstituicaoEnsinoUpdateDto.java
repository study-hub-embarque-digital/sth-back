package com.studyhub.sth.application.dtos.instituicaoEnsino;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoEnsinoUpdateDto {
    private String nome;
    private String endereco;
    private String coordenador;
    private String telefone;
    private String email;
    private String site;
    private Boolean isActive;
}





