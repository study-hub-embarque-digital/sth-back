package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.duvida.*;
import com.studyhub.sth.application.dtos.solucao.SolucaoDto;
import com.studyhub.sth.application.dtos.tag.TagDto;
import com.studyhub.sth.domain.before.entities.Duvida;
import com.studyhub.sth.domain.before.entities.Tag;
import com.studyhub.sth.domain.before.entities.Usuario;
import com.studyhub.sth.domain.before.repositories.IDuvidaRepository;
import com.studyhub.sth.domain.before.repositories.ITagRepository;
import com.studyhub.sth.domain.before.repositories.IUsuarioRepository;
import com.studyhub.sth.domain.before.services.IDuvidaService;
import com.studyhub.sth.libs.mapper.IMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DuvidaService implements IDuvidaService{
    @Autowired
    private IDuvidaRepository duvidaRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ITagRepository tagRepository;

    @Autowired
    private IMapper mapper;

    @Override
    public List<DuvidaDto> findAll() {
        return this.duvidaRepository
                .findAll()
                .stream()
                .map(d -> {
                    DuvidaDto dto = this.mapper.map(d, DuvidaDto.class);
                    dto.setNomeUsuario(d.getUsuario().getNome());
                    dto.setTags(d.getTags().stream().map(Tag::getNome).toList());
                    return dto;
                })
                .toList();
    } 

    @Override
    public DuvidaSolucoesDto findByIdWithSolucao(UUID id) {
        return this.duvidaRepository.findById(id)
                .map(d -> {
                    DuvidaDto duvidaDto = this.mapper.map(d, DuvidaDto.class);
                    duvidaDto.setNomeUsuario(d.getUsuario().getNome());
                    duvidaDto.setTags(d.getTags().stream().map(Tag::getNome).toList());

                    List<SolucaoDto> solucoesDto = d.getSolucoes().stream()
                        .map(solucao -> this.mapper.map(solucao, SolucaoDto.class))
                        .collect(Collectors.toList());

                    DuvidaSolucoesDto dto = new DuvidaSolucoesDto();
                    dto.setDuvida(duvidaDto);
                    dto.setSolucoes(solucoesDto);
                    return dto;
                })
                .orElseThrow(() -> new RuntimeException("Dúvida não encontrada"));
    }

    @Override
    @Transactional
    public DuvidaDto create(NewDuvidaDto newDuvidaDto){
        Usuario usuario = usuarioRepository.findById(newDuvidaDto.getUsuario()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Duvida duvida = this.mapper.map(newDuvidaDto,Duvida.class);
        duvida.setUsuario(usuario);
        this.duvidaRepository.save(duvida);

        DuvidaDto duvidaDto = this.mapper.map(duvida, DuvidaDto.class);
        duvidaDto.setNomeUsuario(usuario.getNome());
        return duvidaDto;
    }

    @Override
    public DuvidaDto update(UUID duvidaId, UpdateDuvidaDto updateDuvidaDto){

        Duvida duvida = duvidaRepository.findById(duvidaId).orElseThrow(() -> new RuntimeException("Dúvida não encontrada"));
        duvida.setTitulo(updateDuvidaDto.getTitulo());
        duvida.setDescricao(updateDuvidaDto.getDescricao());
        List<Tag> tags = tagRepository.findAllById(updateDuvidaDto.getTags().stream().map(TagDto::getId).toList());
        duvida.setTags(tags);
        duvidaRepository.save(duvida);

        DuvidaDto dto = this.mapper.map(duvida,DuvidaDto.class);
        return dto;
    }

    @Override
    public void delete(UUID duvidaId){
        Duvida duvida = duvidaRepository.findById(duvidaId).orElseThrow(() -> new RuntimeException("Dúvida não encontrada"));
        duvidaRepository.deleteById(duvidaId);
    }
    
}
