package com.studyhub.sth.application.dtos.rooms;

import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    UUID roomId;
    UUID criador;
    String description;
    String title;
    String image;
    List<ConteudoEstudoDto> conteudosRecomendados;
}
