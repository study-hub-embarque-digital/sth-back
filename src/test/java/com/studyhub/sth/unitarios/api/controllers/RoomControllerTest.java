package com.studyhub.sth.unitarios.api.controllers;

import com.studyhub.sth.api.controllers.RoomController;
import com.studyhub.sth.application.dtos.rooms.RoomCreateDto;
import com.studyhub.sth.application.dtos.rooms.RoomUpdateDto;
import com.studyhub.sth.application.dtos.rooms.RoomDto;
import com.studyhub.sth.domain.before.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.before.services.IRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RoomControllerTest {

    @Mock
    private IRoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criar_RoomComSucesso() {
        // Arrange
        RoomCreateDto novoRoomDto = new RoomCreateDto();
        RoomDto roomCriado = new RoomDto();
        when(roomService.criar(novoRoomDto)).thenReturn(roomCriado);

        // Act
        ResponseEntity<RoomDto> response = roomController.criar(novoRoomDto);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(roomCriado, response.getBody());
        verify(roomService, times(1)).criar(novoRoomDto);
    }

    @Test
    void atualizar_RoomComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID roomId = UUID.randomUUID();
        RoomUpdateDto roomAtualizadaDto = new RoomUpdateDto();
        RoomDto roomAtualizado = new RoomDto();
        when(roomService.atualizar(roomId, roomAtualizadaDto)).thenReturn(roomAtualizado);

        // Act
        ResponseEntity<RoomDto> response = roomController.atualizar(roomId, roomAtualizadaDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(roomAtualizado, response.getBody());
        verify(roomService, times(1)).atualizar(roomId, roomAtualizadaDto);
    }

    @Test
    void detalhar_RoomComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID roomId = UUID.randomUUID();
        RoomDto roomDetalhado = new RoomDto();
        when(roomService.detalhar(roomId)).thenReturn(roomDetalhado);

        // Act
        ResponseEntity<RoomDto> response = roomController.detalhar(roomId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(roomDetalhado, response.getBody());
        verify(roomService, times(1)).detalhar(roomId);
    }

    @Test
    void listar_RoomsComSucesso() {
        // Arrange
        List<RoomDto> rooms = Arrays.asList(new RoomDto(), new RoomDto());
        when(roomService.listar()).thenReturn(rooms);

        // Act
        ResponseEntity<List<RoomDto>> response = roomController.listar();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(rooms, response.getBody());
        verify(roomService, times(1)).listar();
    }

    @Test
    void deletar_RoomComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID roomId = UUID.randomUUID();

        // Act
        ResponseEntity response = roomController.deletar(roomId);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(roomService, times(1)).deletar(roomId);
    }
}
