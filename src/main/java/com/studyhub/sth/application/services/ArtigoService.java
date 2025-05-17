package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.artigo.ArtigoCreateDto;
import com.studyhub.sth.application.dtos.artigo.ArtigoDto;
import com.studyhub.sth.application.dtos.artigo.ArtigoUpdateDto;
import com.studyhub.sth.application.dtos.duvida.DuvidaDto;
import com.studyhub.sth.application.dtos.duvida.NewDuvidaDto;
import com.studyhub.sth.application.dtos.tag.TagDto;
import com.studyhub.sth.domain.entities.Artigo;
import com.studyhub.sth.domain.entities.Duvida;
import com.studyhub.sth.domain.entities.Tag;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.repositories.IArtigoRepository;
import com.studyhub.sth.domain.repositories.ITagRepository;
import com.studyhub.sth.domain.repositories.IUsuarioRepository;
import com.studyhub.sth.domain.services.IArtigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArtigoService implements IArtigoService {

    @Autowired
    private IArtigoRepository artigoRepository;

    @Autowired
    private ITagRepository tagRepository;

    @Autowired
    private IUsuarioRepository usuarioRepositorio;

    @Autowired
    private IMapper mapper;

    @Override
    @Transactional
    public ArtigoDto criar(ArtigoCreateDto dto) {
        Usuario usuario = usuarioRepositorio.findById(dto.getUsuario()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Artigo artigo = this.mapper.map(dto, Artigo.class);
        artigo.setAutor(usuario);
        this.artigoRepository.save(artigo);
        return this.mapper.map(artigo, ArtigoDto.class);
    }

    @Override
    public List<ArtigoDto> listarArtigos() {
        var lista = this.artigoRepository.findAll();
        return lista.stream().map(artigo -> this.mapper.map(artigo, ArtigoDto.class)).collect(Collectors.toList());
    }

    @Override
    public ArtigoDto buscarArtigoPorId(UUID id) {
        var artigo = this.artigoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Artigo não encontrado"));
        return this.mapper.map(artigo, ArtigoDto.class);
    }

    @Override
    public List<ArtigoDto> buscarArtigoPorTitulo(String titulo) {
        var lista = this.artigoRepository.findByTituloContainsIgnoreCase(titulo);
        return lista.stream().map(artigo -> this.mapper.map(artigo, ArtigoDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ArtigoDto> buscarArtigoPorAutor(UUID autorId) {
        this.usuarioRepositorio.findById(autorId).orElseThrow(() -> new EntityNotFoundException("Autor não encontrado!"));
        var lista = this.artigoRepository.findByAutorUsuarioId(autorId);
        return lista.stream().map(artigo -> this.mapper.map(artigo, ArtigoDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ArtigoDto> buscarArtigoPorTag(UUID tagId) {
        this.tagRepository.findById(tagId).orElseThrow(() -> new EntityNotFoundException("Tag não encontrada!"));
        var lista = this.artigoRepository.findByTagId(tagId);
        return lista.stream().map(artigo -> this.mapper.map(artigo, ArtigoDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ArtigoDto atualizar(UUID id, ArtigoUpdateDto dto) {
        var artigo = this.artigoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Artigo não encontrado"));
        if (dto.getTitulo() != null){
            artigo.setTitulo(dto.getTitulo());
        }
        if (dto.getConteudo() != null){
            artigo.setConteudo(dto.getConteudo());
        }
        this.artigoRepository.save(artigo);
        return this.mapper.map(artigo, ArtigoDto.class);
    }

    @Override
    @Transactional
    public ArtigoDto adicionarTag(UUID artigoId, List<TagDto> tags) {
        var artigo = this.artigoRepository.findById(artigoId).orElseThrow(() -> new EntityNotFoundException("Artigo não encontrado"));
        for (TagDto t : tags){
           Tag tag = this.tagRepository.findById(t.getId()).orElseThrow(() -> new EntityNotFoundException("Tag não encontrada!"));
           artigo.getTags().add(tag);
        }
        this.artigoRepository.save(artigo);
        return  this.mapper.map(artigo, ArtigoDto.class);
    }

    @Override
    @Transactional
    public ArtigoDto removerTag(UUID artigoId, UUID tagId) {
        var artigo = this.artigoRepository.findById(artigoId).orElseThrow(() -> new EntityNotFoundException("Artigo não encontrado"));
        var tag = this.tagRepository.findById(tagId).orElseThrow(() -> new EntityNotFoundException("Tag não encontrada!"));
        if (artigo.getTags().remove(tag)) {
            this.artigoRepository.save(artigo);
        } else {
            throw new IllegalStateException("A tag não estava associada ao artigo");
        }
        return  this.mapper.map(artigo, ArtigoDto.class);
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        var artigo = this.artigoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Artigo não encontrado"));
        this.artigoRepository.delete(artigo);
    }
}
