package com.studyhub.sth.dtos.conteudoEstudo;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ConteudoEstudoDto {
    private UUID id;
    private String link;
    private UUID roomId;
}
