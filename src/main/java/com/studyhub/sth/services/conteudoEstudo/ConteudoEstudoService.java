package com.studyhub.sth.services.conteudoEstudo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyhub.sth.dtos.conteudoEstudo.ConteudoEstudSemRoomoDto;
import com.studyhub.sth.dtos.conteudoEstudo.ConteudoEstudoDto;
import com.studyhub.sth.entities.ConteudoEstudo;
import com.studyhub.sth.entities.Room;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.repositories.IConteudoEstudoRepository;
import com.studyhub.sth.repositories.IRoomRepository;

@Service
public class ConteudoEstudoService implements IConteudoEstudoService {
    @Autowired
    private IConteudoEstudoRepository conteudoEstudoRepository;

    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IMapper mapper;

    @Override
    public ConteudoEstudo criarConteudoEstudo(ConteudoEstudoDto dto) {
        ConteudoEstudo conteudoEstudo = new ConteudoEstudo();
        conteudoEstudo.setLink(dto.getLink());

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room não encontrado"));

        conteudoEstudo.setRoom(room);

        return conteudoEstudoRepository.save(conteudoEstudo);
    }

    @Override
    public List<ConteudoEstudSemRoomoDto> listarConteudosEstudo() {
        List<ConteudoEstudo> lista = this.conteudoEstudoRepository.findAll();
        return lista.stream().map(conteudo -> this.mapper.map(conteudo, ConteudoEstudSemRoomoDto.class)).toList();
    }

    @Override
    public ConteudoEstudo obterConteudoEstudoPorId(UUID id) {
        return conteudoEstudoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo de estudo não encontrado"));
    }

    @Override
    public ConteudoEstudo atualizarConteudoEstudo(UUID id, ConteudoEstudoDto dto) {
        ConteudoEstudo conteudoEstudo = obterConteudoEstudoPorId(id);
        conteudoEstudo.setLink(dto.getLink());

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room não encontrado"));
        conteudoEstudo.setRoom(room);

        return conteudoEstudoRepository.save(conteudoEstudo);
    }

    @Override
    public void deletarConteudoEstudo(UUID id) {
        conteudoEstudoRepository.deleteById(id);
    }
}
