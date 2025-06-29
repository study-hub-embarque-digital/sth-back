package com.studyhub.sth.application.dtos.job;

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
public class JobCreateDto {
    private String cargo;
    private String areaAtuacao;
    private Date dataInicio;
    private UUID alunoId;
    private UUID empregadorId;
    private TipoVinculo tipoVinculo;
    private  String atividadesDesenvolvidas;
}
