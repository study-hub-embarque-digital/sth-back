package com.studyhub.sth.application.dtos.conteudoEstudo;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConteudoEstudoCreateDto {
    private String link;
    private UUID roomId;
}
