package com.studyhub.sth.application.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoUpdateDto;
import com.studyhub.sth.domain.services.IConteudoEstudoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoCreateDto;
import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoDto;
import com.studyhub.sth.domain.entities.ConteudoEstudo;
import com.studyhub.sth.domain.entities.Room;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.repositories.IConteudoEstudoRepository;
import com.studyhub.sth.domain.repositories.IRoomRepository;

@Service
public class ConteudoEstudoService implements IConteudoEstudoService {
    @Autowired
    private IConteudoEstudoRepository conteudoEstudoRepository;

    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IMapper mapper;

    @Override
    @Transactional
    public ConteudoEstudoCreateDto criarConteudoEstudo(ConteudoEstudoCreateDto dto) {
        ConteudoEstudo conteudoEstudo =  this.mapper.map(dto, ConteudoEstudo.class);
        conteudoEstudoRepository.save(conteudoEstudo);
        return this.mapper.map(conteudoEstudo, ConteudoEstudoCreateDto.class);
    }

    @Override
    public List<ConteudoEstudoCreateDto> listarConteudosEstudo() {
        List<ConteudoEstudo> lista = this.conteudoEstudoRepository.findAll();
        return lista.stream().map(conteudo -> this.mapper.map(conteudo, ConteudoEstudoCreateDto.class)).collect(Collectors.toList());
    }

    @Override
    public ConteudoEstudoDto obterConteudoEstudoPorId(UUID id) {
        var contedoEstconteudo = this.conteudoEstudoRepository.findById(id).orElseThrow(() -> new RuntimeException("Conteúdo de estudo não encontrado"));
        return this.mapper.map(contedoEstconteudo, ConteudoEstudoDto.class);
    }

    @Override
    @Transactional
    public ConteudoEstudoDto atualizarConteudoEstudo(UUID id, ConteudoEstudoUpdateDto dto) {
        var conteudoEstudo = this.conteudoEstudoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Conteúdo de estudo não encontrado"));
        if (dto.getLink() != null){
            conteudoEstudo.setLink(dto.getLink());
        }
        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room não encontrado"));
        conteudoEstudo.setRoom(room);
        conteudoEstudoRepository.save(conteudoEstudo);
        return this.mapper.map(conteudoEstudo, ConteudoEstudoDto.class);
    }

    @Override
    @Transactional
    public void deletarConteudoEstudo(UUID id) {
        var conteudoEstudo = this.conteudoEstudoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Conteo de estudo não encontrado"));
        conteudoEstudoRepository.delete(conteudoEstudo);
    }
}
