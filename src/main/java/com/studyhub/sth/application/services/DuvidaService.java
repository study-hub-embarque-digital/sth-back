package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.duvida.*;
import com.studyhub.sth.application.dtos.solucao.SolucaoDto;
import com.studyhub.sth.application.dtos.tag.TagDto;
import com.studyhub.sth.domain.entities.Duvida;
import com.studyhub.sth.domain.entities.Tag;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.repositories.*;
import com.studyhub.sth.domain.services.IDuvidaService;
import com.studyhub.sth.libs.mapper.IMapper;

import jakarta.persistence.EntityNotFoundException;
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
    private ISolucaoRepository solucaoRepository;

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
                    long quantidade = solucaoRepository.contarSolucoesPorDuvida(d.getDuvidaId());
                    dto.setQuantidadeSolucoes(quantidade);
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
                    duvidaDto.setUsuarioId(d.getUsuario().getUsuarioId());
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

    @Transactional
    public void marcarComoResolvida(UUID duvidaId) {
    Duvida duvida = duvidaRepository.findById(duvidaId)
        .orElseThrow(() -> new EntityNotFoundException("Dúvida não encontrada"));

    duvida.setResolvida(true);
    duvidaRepository.save(duvida);
}

    
}
