package com.studyhub.sth.dtos.instituicaoEnsino;

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
public class InstituicaoEnsinoDto {
    private UUID id;
    private String nome;
    private String endereco;
    private String coordenador;
    private List<UUID> alunos;
    private List<UUID> squads;

}