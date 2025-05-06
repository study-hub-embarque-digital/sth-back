package com.studyhub.sth.domain.before.services;

import com.studyhub.sth.application.dtos.artigo.ArtigoCreateDto;
import com.studyhub.sth.application.dtos.artigo.ArtigoDto;
import com.studyhub.sth.application.dtos.artigo.ArtigoUpdateDto;
import com.studyhub.sth.application.dtos.tag.TagDto;

import java.util.List;
import java.util.UUID;

public interface IArtigoService {
    ArtigoDto criar(ArtigoCreateDto dto);
    ArtigoDto atualizar(UUID id, ArtigoUpdateDto dto);
    List<ArtigoDto> listarArtigos();
    ArtigoDto buscarArtigoPorId(UUID id);
    List<ArtigoDto> buscarArtigoPorTitulo(String titulo);
    List<ArtigoDto> buscarArtigoPorAutor(UUID autorId);
    List<ArtigoDto> buscarArtigoPorTag(UUID tagId);
    ArtigoDto adicionarTag(UUID artigoId,List<TagDto> tags);
    ArtigoDto removerTag(UUID artigoId, UUID tagId);
    void deletar(UUID id);
}
