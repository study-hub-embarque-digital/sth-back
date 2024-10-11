package com.studyhub.sth.dtos.conteudoEstudo;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConteudoEstudSemRoomoDto {
    private String link;
    private UUID roomId;

}
