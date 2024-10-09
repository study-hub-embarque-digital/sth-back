package com.studyhub.sth.dtos.InstituicaoEnsino;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoEnsinoDto {

    private UUID instituicaoEnsinoId;
    private String nome;
    private String endereco;
    private String coordenador;
    //private List<UUID> alunos; // Ou outro DTO de Aluno
    //private List<UUID> squads; // Ou outro DTO de Squad

}