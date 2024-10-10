package com.studyhub.sth.services.rooms;

import com.studyhub.sth.dtos.rooms.RoomAtualizadaDto;
import com.studyhub.sth.dtos.rooms.RoomDto;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.repositories.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoomService implements IRoomService {
    @Autowired
    private IRoomRepository roomRepository;

    @Override
    public RoomDto criar(RoomDto novoRoomDto) {
        // Lógica de criação
        return new RoomDto();
    }

    @Override
    public RoomDto atualizar(UUID roomId, RoomAtualizadaDto roomAtualizadaDto) throws ElementoNaoEncontradoExcecao {
        // Lógica de atualização
        return new RoomDto();
    }

    @Override
    public RoomDto detalhar(UUID roomId) throws ElementoNaoEncontradoExcecao {
        // Lógica de detalhamento
        return new RoomDto();
    }
}
