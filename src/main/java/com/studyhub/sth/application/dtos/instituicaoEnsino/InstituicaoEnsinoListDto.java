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
public class InstituicaoEnsinoListDto {
    private UUID InstituicaoEnsinoId;
    private String razaoSocial;
    private String nomeFantasia;
    private String telefone;
    private String email;
    private String cnpj;
    private String nome;
    private Boolean isActive;
    private String site;
}
