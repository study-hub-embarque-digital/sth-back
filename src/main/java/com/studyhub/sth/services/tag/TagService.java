package com.studyhub.sth.services.tag;

import com.studyhub.sth.dtos.tag.TagCreateAndUpdateDTO;
import com.studyhub.sth.dtos.tag.TagDto;
import com.studyhub.sth.entities.Tag;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.repositories.ITagRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TagService implements ITagService{
    @Autowired
    private IMapper mapper;

    @Autowired
    private ITagRepository tagRepository;

    @Override
    @Transactional
    public TagDto criar(TagCreateAndUpdateDTO dto) {
        Tag tag = this.mapper.map(dto, Tag.class);
        this.tagRepository.save(tag);
        return this.mapper.map(tag, TagDto.class);
    }

    @Override
    public List<TagDto> listar() {
        var lista = this.tagRepository.findAll();
        return lista.stream().map(tag -> this.mapper.map(tag, TagDto.class)).collect(Collectors.toList());
    }

    @Override
    public TagDto buscarPorId(UUID id) {
        var tag = this.tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag não encontrada"));
        return this.mapper.map(tag, TagDto.class);
    }

    @Override
    public TagDto buscarPorNome(String nome) {
        var tag = this.tagRepository.findByNomeContainsIgnoreCase(nome).orElseThrow(() -> new EntityNotFoundException("Tag não encontrada"));
        return this.mapper.map(tag, TagDto.class);
    }

    @Override
    @Transactional
    public TagDto atualizar(UUID id, TagCreateAndUpdateDTO dto) {
        var tag = this.tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag não encontrada"));
        if(dto.getNome() != null){
            tag.setNome(dto.getNome());
        }
        this.tagRepository.save(tag);
        return this.mapper.map(tag, TagDto.class);
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        var tag = this.tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag não encontrada"));
        this.tagRepository.delete(tag);
    }
}
