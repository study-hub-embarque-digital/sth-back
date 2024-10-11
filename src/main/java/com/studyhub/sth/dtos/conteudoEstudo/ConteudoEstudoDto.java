package com.studyhub.sth.dtos.conteudoEstudo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConteudoEstudoDto {
    private UUID conteudoEstudoId;
    private String link;
    private UUID roomId;
}
