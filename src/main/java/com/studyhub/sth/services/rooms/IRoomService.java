package com.studyhub.sth.services.rooms;

import com.studyhub.sth.dtos.rooms.RoomAtualizadaDto;
import com.studyhub.sth.dtos.rooms.RoomDto;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;

import java.util.List;
import java.util.UUID;

public interface IRoomService {
    RoomDto criar(RoomDto novoRoomDto);
    RoomDto atualizar(UUID roomId, RoomAtualizadaDto roomAtualizadaDto) throws ElementoNaoEncontradoExcecao;
    RoomDto detalhar(UUID roomId) throws ElementoNaoEncontradoExcecao;
    void deletar(UUID roomId) throws ElementoNaoEncontradoExcecao;
    List<RoomDto> listar();
}
