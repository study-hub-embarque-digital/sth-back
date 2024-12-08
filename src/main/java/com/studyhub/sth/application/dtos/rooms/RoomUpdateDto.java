package com.studyhub.sth.application.dtos.rooms;

import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomUpdateDto {
    ConteudoEstudoDto conteudosRecomendados;
    //UUID cicloId;
   // UUID formularioId;
}
