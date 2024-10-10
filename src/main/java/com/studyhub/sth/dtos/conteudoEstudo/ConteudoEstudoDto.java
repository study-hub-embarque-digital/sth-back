package com.studyhub.sth.dtos.conteudoEstudo;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ConteudoEstudoDto {
    private UUID conteudoEstudoId;
    private String link;
    private UUID roomId;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public UUID getRoom() {
        return roomId;
    }

    public void setRoom(UUID room) {
        this.roomId = room;
    }
}
