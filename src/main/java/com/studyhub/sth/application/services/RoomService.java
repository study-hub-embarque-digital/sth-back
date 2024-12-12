package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.rooms.RoomCreateDto;
import com.studyhub.sth.application.dtos.rooms.RoomUpdateDto;
import com.studyhub.sth.application.dtos.rooms.RoomDto;
import com.studyhub.sth.domain.entities.Room;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IRoomService;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.repositories.IConteudoEstudoRepository;
import com.studyhub.sth.domain.repositories.IRoomRepository;
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
        if (roomAtualizadaDto.getConteudosRecomendados() != null) {
            var conteudoEstudoDto = roomAtualizadaDto.getConteudosRecomendados();
            var conteudoEstudo = this.roomRepository.findConteudoEstudoByRoomIdAndConteudoEstudoId(
                            roomId, conteudoEstudoDto.getRoomId())
                    .orElseThrow(() -> new ElementoNaoEncontradoExcecao("Conteúdo de Estudo não encontrado!"));

            conteudoEstudo.setLink(conteudoEstudoDto.getLink());
            this.conteudoEstudoRepository.save(conteudoEstudo);
        }
        if (roomAtualizadaDto.getDescription() != null){
            room.setDescription(roomAtualizadaDto.getDescription());
        }
        if(roomAtualizadaDto.getImage() != null){
            room.setImage(roomAtualizadaDto.getImage());
        }
        if(roomAtualizadaDto.getTitle() != null){
            room.setTitle(roomAtualizadaDto.getTitle());
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
