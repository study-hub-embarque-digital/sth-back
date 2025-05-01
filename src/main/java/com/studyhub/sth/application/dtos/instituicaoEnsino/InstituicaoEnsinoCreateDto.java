package com.studyhub.sth.application.dtos.instituicaoEnsino;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituicaoEnsinoCreateDto {
    private String nome;
    private String endereco;
    private String coordenador;
    private String razaoSocial;
    private String nomeFantasia;
    private String telefone;
    private String email;
    private String cnpj;
    private boolean isActive;
    private String site;
}

