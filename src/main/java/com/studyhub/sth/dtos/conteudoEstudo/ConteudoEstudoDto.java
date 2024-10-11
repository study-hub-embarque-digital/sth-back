package com.studyhub.sth.dtos.conteudoEstudo;

import java.util.UUID;

public class ConteudoEstudoDto {
    private String link;
    private UUID roomId;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }
}
