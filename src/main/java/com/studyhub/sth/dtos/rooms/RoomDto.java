package com.studyhub.sth.dtos.rooms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    UUID roomId;
    String conteudosRecomendados;  // Lista de IDs ou objetos simplificados
    UUID cicloId;
    UUID formularioId;
}
