package com.studyhub.sth.application.dtos.empresas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaListDto {
    private UUID empresaId;
    private String nomeFantasia;
}
