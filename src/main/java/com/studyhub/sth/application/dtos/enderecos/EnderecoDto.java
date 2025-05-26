package com.studyhub.sth.application.dtos.enderecos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {
    private String cep;
    private String logradouro;
    private String cidade;
    private String estado;
    private String complemento;
    private String numero;
}
