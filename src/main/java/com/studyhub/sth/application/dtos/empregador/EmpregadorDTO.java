package com.studyhub.sth.application.dtos.empregador;

import com.studyhub.sth.domain.enums.TipoVinculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpregadorDTO {
    private String nomeGestor;
    private String cargoGestor;
    private String emailGestor;
    private String nomeEmpresa;
    private String cnpjEmpresa;
    private TipoVinculo tipoVinculo;
    private String cargoDetalhado;
    private  String atividadesDesenvolvidas;
}
