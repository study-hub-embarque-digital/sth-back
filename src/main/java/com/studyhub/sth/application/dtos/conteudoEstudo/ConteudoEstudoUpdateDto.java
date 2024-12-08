package com.studyhub.sth.application.dtos.conteudoEstudo;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ConteudoEstudoUpdateDto {
    private UUID roomId;
    private String link;
}
