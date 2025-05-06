package com.studyhub.sth.application.dtos.empregador;

import com.studyhub.sth.domain.before.enums.TipoVinculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpregadorUpdateDto {
    private String nomeGestor;
    private  String cargoGestor;
    private String emailGestor;
    private TipoVinculo tipoVinculo;
    private String cargoDetalhado;
    private String atividadesDesenvolvidas;
}
