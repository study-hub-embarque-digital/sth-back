package com.studyhub.sth.application.dtos.instituicaoEnsino;

import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
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
    private UUID instituicaoEnsinoId;
    private String nome;
    private String endereco;
    private String coordenador;
    private String razaoSocial;
    private String nomeFantasia;
    private String telefone;
    private String email;
    private String cnpj;
    private Boolean isActive;
    private String site;
    private List<AlunoDto> alunos;
    private List<SquadDTO> squads;

}