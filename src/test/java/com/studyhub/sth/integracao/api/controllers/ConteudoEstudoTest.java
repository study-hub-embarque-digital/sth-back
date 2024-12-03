package com.studyhub.sth.integracao.api.controllers;

import com.studyhub.sth.domain.entities.ConteudoEstudo;
import com.studyhub.sth.domain.entities.Room;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ConteudoEstudoTest {
    @Test
    public void testSettersAndGetters() {
        // Arrange
        ConteudoEstudo conteudoEstudo = new ConteudoEstudo();
        UUID id = UUID.randomUUID();
        String link = "https://example.com";
        Room room = new Room();

        // Act
        conteudoEstudo.setConteudoEstudoId(id);
        conteudoEstudo.setLink(link);
        conteudoEstudo.setRoom(room);

        // Assert
        assertEquals(id, conteudoEstudo.getConteudoEstudoId());
        assertEquals(link, conteudoEstudo.getLink());
        assertEquals(room, conteudoEstudo.getRoom());
    }
}
