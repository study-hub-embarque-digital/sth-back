package com.studyhub.sth.application.dtos.job;

import com.studyhub.sth.domain.enums.TipoVinculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobUpdateDto {
    private String cargo;
    private String areaAtuacao;
    private Date dataTermino;
    private TipoVinculo tipoVinculo;
    private  String atividadesDesenvolvidas;
}
