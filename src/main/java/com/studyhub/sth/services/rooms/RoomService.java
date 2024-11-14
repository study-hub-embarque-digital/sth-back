package com.studyhub.sth.services.rooms;

import com.studyhub.sth.dtos.rooms.RoomCreateDto;
import com.studyhub.sth.dtos.rooms.RoomUpdateDto;
import com.studyhub.sth.dtos.rooms.RoomDto;
import com.studyhub.sth.entities.Room;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.repositories.IConteudoEstudoRepository;
import com.studyhub.sth.repositories.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomService implements IRoomService {
    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IConteudoEstudoRepository conteudoEstudoRepository;

    @Autowired
    private IMapper mapper;

    @Override
    public RoomDto criar(RoomCreateDto novoRoomDto) {
        Room room = this.mapper.map(novoRoomDto, Room.class);
        this.roomRepository.save(room);
        return this.mapper.map(room, RoomDto.class);
    }

    @Override
    public RoomDto atualizar(UUID roomId, RoomUpdateDto roomAtualizadaDto) throws ElementoNaoEncontradoExcecao {
        var room = this.roomRepository.findById(roomId)
                .orElseThrow(() -> new ElementoNaoEncontradoExcecao("O Room não foi encontrado!"));
        if (roomAtualizadaDto.getConteudosRecomendados().getLink() != null) {
            var conteudoEstudoDto = roomAtualizadaDto.getConteudosRecomendados();
            var conteudoEstudo = this.roomRepository.findConteudoEstudoByRoomIdAndConteudoEstudoId(
                            roomId, conteudoEstudoDto.getRoomId())
                    .orElseThrow(() -> new ElementoNaoEncontradoExcecao("Conteúdo de Estudo não encontrado!"));

            conteudoEstudo.setLink(conteudoEstudoDto.getLink());
            this.conteudoEstudoRepository.save(conteudoEstudo);
        }
        this.roomRepository.save(room);
        return this.mapper.map(room, RoomDto.class);
    }

    @Override
    public RoomDto detalhar(UUID roomId) throws ElementoNaoEncontradoExcecao {
        var room = this.roomRepository.findById(roomId).orElseThrow(()-> new ElementoNaoEncontradoExcecao("O Room não foi encontrado!"));
        return this.mapper.map(room, RoomDto.class);
    }

    @Override
    public void deletar(UUID roomId) throws ElementoNaoEncontradoExcecao {
        var room = this.roomRepository.findById(roomId).orElseThrow(()-> new ElementoNaoEncontradoExcecao("O Room não foi encontrado!"));
        this.roomRepository.delete(room);
    }

    @Override
    public List<RoomDto> listar() {
        var lista = this.roomRepository.findAll();
        return lista.stream().map(room -> this.mapper.map(room, RoomDto.class)).collect(Collectors.toList());
    }

}
