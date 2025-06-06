package com.studyhub.sth.domain.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studyhub.sth.application.dtos.rooms.GeneratedRoomDto;
import com.studyhub.sth.application.dtos.rooms.RoomCreateDto;
import com.studyhub.sth.application.dtos.rooms.RoomUpdateDto;
import com.studyhub.sth.application.dtos.rooms.RoomDto;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IRoomService {
    RoomDto criar(RoomCreateDto novoRoomDto);
    RoomDto atualizar(UUID roomId, RoomUpdateDto roomAtualizadaDto) throws ElementoNaoEncontradoExcecao;
    RoomDto detalhar(UUID roomId) throws ElementoNaoEncontradoExcecao;
    void deletar(UUID roomId) throws ElementoNaoEncontradoExcecao;
    List<RoomDto> listar();
    public GeneratedRoomDto generateRoom() throws IOException;
    void teste();
}
