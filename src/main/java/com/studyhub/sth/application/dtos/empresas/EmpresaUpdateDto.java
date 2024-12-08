package com.studyhub.sth.application.dtos.empresas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaUpdateDto {
    private String nomeFantasia;
    private String telefone;
    private String email;
}
