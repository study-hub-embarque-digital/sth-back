package com.studyhub.sth.application.dtos.solucao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSolucaoDto {
    private String descricao;
    private UUID duvida;
    private UUID usuario;
}
