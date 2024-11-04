package com.studyhub.sth.services.tag;

import com.studyhub.sth.dtos.tag.TagCreateAndUpdateDTO;
import com.studyhub.sth.dtos.tag.TagDto;

import java.util.List;
import java.util.UUID;

public interface ITagService {
    TagDto criar(TagCreateAndUpdateDTO dto);
    List<TagDto> listar();
    TagDto buscarPorId(UUID id);
    TagDto buscarPorNome(String nome);
    TagDto atualizar(UUID id, TagCreateAndUpdateDTO dto);
    void deletar(UUID id);
}
