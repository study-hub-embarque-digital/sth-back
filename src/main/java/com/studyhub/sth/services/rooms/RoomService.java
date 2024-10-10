package com.studyhub.sth.services.rooms;

import com.studyhub.sth.dtos.conteudoEstudo.ConteudoEstudoDto;
import com.studyhub.sth.dtos.rooms.NovoRoomDto;
import com.studyhub.sth.dtos.rooms.RoomAtualizadaDto;
import com.studyhub.sth.dtos.rooms.RoomDto;
import com.studyhub.sth.dtos.users.UsuarioDto;
import com.studyhub.sth.entities.ConteudoEstudo;
import com.studyhub.sth.entities.Room;
import com.studyhub.sth.entities.Usuario;
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
    public RoomDto criar(NovoRoomDto novoRoomDto) {
        Room room = this.mapper.map(novoRoomDto, Room.class);
        this.roomRepository.save(room);
        return this.mapper.map(room, RoomDto.class);
    }

    @Override
    public RoomDto atualizar(UUID roomId, RoomAtualizadaDto roomAtualizadaDto) throws ElementoNaoEncontradoExcecao {
        var room = this.roomRepository.findById(roomId)
                .orElseThrow(() -> new ElementoNaoEncontradoExcecao("O Room não foi encontrado!"));
        if (roomAtualizadaDto.getConteudosRecomendados().getLink() != null) {
            var conteudoEstudoDto = roomAtualizadaDto.getConteudosRecomendados();
            var conteudoEstudo = this.roomRepository.findConteudoEstudoByRoomIdAndConteudoEstudoId(
                            roomId, conteudoEstudoDto.getConteudoEstudoId())
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

    @Override
    public List<ConteudoEstudoDto> addConteudoRecomendado(UUID roomId,ConteudoEstudoDto conteudoEstudoDto) throws ElementoNaoEncontradoExcecao {
        var room = this.roomRepository.findById(roomId).orElseThrow(()-> new ElementoNaoEncontradoExcecao("O room não foi encontrado!"));
        room.getConteudosRecomendados().add(this.mapper.map(conteudoEstudoDto, ConteudoEstudo.class));
        this.roomRepository.save(room);
        var lista = room.getConteudosRecomendados();
        return lista.stream().map( conteudo -> this.mapper.map(conteudo, ConteudoEstudoDto.class)).collect(Collectors.toList());
    }
}
