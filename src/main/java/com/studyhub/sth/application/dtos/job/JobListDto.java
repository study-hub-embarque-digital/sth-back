package com.studyhub.sth.application.dtos.job;

import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.empregador.EmpregadorDTO;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.domain.enums.TipoVinculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobListDto {
    private UUID jobId;
    private String cargo;
    private String areaAtuacao;
    private Date dataInicio;
    private Date dataTermino;
    private TipoVinculo tipoVinculo;
    private  String atividadesDesenvolvidas;
    private AlunoDto alunoDto;
    private EmpregadorDTO empregadorDTO;
}
