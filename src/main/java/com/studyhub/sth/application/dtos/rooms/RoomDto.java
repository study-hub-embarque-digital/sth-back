package com.studyhub.sth.application.dtos.rooms;

import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoDto;
import com.studyhub.sth.domain.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    UUID roomId;
    String description;
    String title;
    String image;
    List<ConteudoEstudoDto> conteudosRecomendados;

    public RoomDto(Room room) {
        this.roomId = room.getRoomId();
        this.description = room.getDescription();
        this.title = room.getTitle();
        this.image = room.getImage();
        this.setConteudosRecomendados(new ArrayList<>());
        this.conteudosRecomendados = room.getConteudosRecomendados().stream().map(ConteudoEstudoDto::new).toList();
    }
}
