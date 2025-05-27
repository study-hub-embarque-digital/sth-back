package com.studyhub.sth.application.dtos.squad;

import com.studyhub.sth.application.dtos.alunos.AlunoListDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoListDto;
import com.studyhub.sth.application.dtos.mentor.MentorListDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteListDto;
import com.studyhub.sth.domain.enums.Ciclo;
import com.studyhub.sth.domain.enums.TipoSquad;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SquadListDTO {
    private String nome;
    private TipoSquad tipo;
    private MentorListDto mentorDto;
    private EmpresaDto empresaDto;
    private InstituicaoEnsinoListDto instituicaoDeEnsinoDto;
    private List<RepresentanteListDto> representanteListDtos;
    private List<AlunoListDto> alunoListDtos;
    @Valid
    private String semestre;
    private Ciclo ciclo;
}
