package com.studyhub.sth.dtos.rooms;

import com.studyhub.sth.dtos.conteudoEstudo.ConteudoEstudoDto;
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
    List<ConteudoEstudoDto> conteudosRecomendados;  // Lista de IDs ou objetos simplificados
}
